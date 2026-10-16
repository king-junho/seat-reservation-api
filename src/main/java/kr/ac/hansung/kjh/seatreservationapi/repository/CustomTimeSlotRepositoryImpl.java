package kr.ac.hansung.kjh.seatreservationapi.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDateTime;
import java.util.List;
import kr.ac.hansung.kjh.seatreservationapi.entity.QTimeSlotEntity;
import kr.ac.hansung.kjh.seatreservationapi.entity.QTimeSlotSeatEntity;
import kr.ac.hansung.kjh.seatreservationapi.repository.projection.QTimeSlotSummary;
import kr.ac.hansung.kjh.seatreservationapi.repository.projection.TimeSlotSummary;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class CustomTimeSlotRepositoryImpl implements CustomTimeSlotRepository {

  private final JPAQueryFactory queryFactory;
  private final QTimeSlotEntity timeSlot = QTimeSlotEntity.timeSlotEntity;
  private final QTimeSlotSeatEntity timeSlotSeat = QTimeSlotSeatEntity.timeSlotSeatEntity;

  @Override
  public Page<TimeSlotSummary> findByFacilityIdAndLocalDate(Long facilityId, LocalDateTime from,
      LocalDateTime to, Pageable pageable) {
    NumberExpression<Long> totalSeats = timeSlotSeat.id.count();
    NumberExpression<Long> occupiedSeats = timeSlotSeat.currentReservationId.count();
    NumberExpression<Long> availableSeats = totalSeats.subtract(occupiedSeats);

    List<TimeSlotSummary> content = queryFactory
        .select(new QTimeSlotSummary(
            timeSlot.id,
            timeSlot.facilityId,
            timeSlot.startTime,
            timeSlot.endTime,
            totalSeats,
            availableSeats
        ))
        .from(timeSlot)
        .leftJoin(timeSlotSeat)
        .on(timeSlotSeat.timeSlotId.eq(timeSlot.id))
        .where(
            isSameFacilityId(facilityId),
            startTimeBetween(from, to)
        )
        .groupBy(timeSlot.id, timeSlot.facilityId, timeSlot.startTime, timeSlot.endTime)
        .orderBy(timeSlot.startTime.asc(), timeSlot.id.asc())
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .fetch();

    JPAQuery<Long> count = queryFactory
        .select(timeSlot.count())
        .from(timeSlot)
        .where(
            isSameFacilityId(facilityId),
            startTimeBetween(from, to)
        );

    return PageableExecutionUtils.getPage(content, pageable, count::fetchOne);
  }

  private BooleanExpression isSameFacilityId(Long facilityId) {
    return timeSlot.facilityId.eq(facilityId);
  }

  private BooleanExpression startTimeBetween(LocalDateTime from, LocalDateTime to) {
    return timeSlot.startTime.goe(from).and(timeSlot.startTime.lt(to));
  }
}
