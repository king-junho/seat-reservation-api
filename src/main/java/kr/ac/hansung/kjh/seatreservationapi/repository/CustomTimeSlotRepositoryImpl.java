package kr.ac.hansung.kjh.seatreservationapi.repository;

import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDateTime;
import java.util.List;
import kr.ac.hansung.kjh.seatreservationapi.dto.response.QTimeSlotResponse;
import kr.ac.hansung.kjh.seatreservationapi.dto.response.TimeSlotResponse;
import kr.ac.hansung.kjh.seatreservationapi.entity.QTimeSlotEntity;
import kr.ac.hansung.kjh.seatreservationapi.entity.QTimeSlotSeatEntity;
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
  public Page<TimeSlotResponse> findByFacilityIdAndLocalDate(Long facilityId, LocalDateTime from,
      LocalDateTime to, Pageable pageable) {
    List<TimeSlotResponse> content = queryFactory
        .select(new QTimeSlotResponse(
            timeSlot.id,
            timeSlot.facilityId,
            timeSlot.startTime,
            timeSlot.endTime,
            timeSlotSeat.id.count().intValue(),
            new CaseBuilder()
                .when(timeSlotSeat.id.isNotNull().and(timeSlotSeat.currentReservationId.isNull()))
                .then(1)
                .otherwise(0)
                .sumLong()
                .intValue()
        ))
        .from(timeSlot)
        .leftJoin(timeSlotSeat)
        .on(timeSlotSeat.timeSlotId.eq(timeSlot.id))
        .where(
            timeSlot.facilityId.eq(facilityId),
            timeSlot.startTime.goe(from),
            timeSlot.startTime.lt(to)
        )
        .groupBy(timeSlot.id, timeSlot.startTime, timeSlot.endTime)
        .orderBy(timeSlot.startTime.asc(), timeSlot.id.asc())
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .fetch();

    JPAQuery<Long> count = queryFactory
        .select(timeSlot.count())
        .from(timeSlot)
        .where(
            timeSlot.facilityId.eq(facilityId),
            timeSlot.startTime.goe(from),
            timeSlot.startTime.lt(to)
        );

    return PageableExecutionUtils.getPage(content, pageable, count::fetchOne);
  }
}
