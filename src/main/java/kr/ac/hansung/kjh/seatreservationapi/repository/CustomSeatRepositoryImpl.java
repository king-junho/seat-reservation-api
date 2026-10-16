package kr.ac.hansung.kjh.seatreservationapi.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import kr.ac.hansung.kjh.seatreservationapi.entity.QSeatEntity;
import kr.ac.hansung.kjh.seatreservationapi.entity.QTimeSlotSeatEntity;
import kr.ac.hansung.kjh.seatreservationapi.repository.projection.QSeatSummary;
import kr.ac.hansung.kjh.seatreservationapi.repository.projection.SeatSummary;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class CustomSeatRepositoryImpl implements CustomSeatRepository {

  private final JPAQueryFactory queryFactory;
  private final QSeatEntity seat = QSeatEntity.seatEntity;
  private final QTimeSlotSeatEntity timeSlotSeat = QTimeSlotSeatEntity.timeSlotSeatEntity;

  @Override
  public Page<SeatSummary> findByFacilityIdAndTimeSlotId(Long facilityId, Long timeSlotId,
      Pageable pageable) {
    List<SeatSummary> content = queryFactory
        .select(new QSeatSummary(
            seat.id,
            seat.facilityId,
            seat.rowLabel,
            seat.columnNo,
            timeSlotSeat.currentReservationId.isNotNull()
        ))
        .from(timeSlotSeat)
        .join(seat)
        .on(timeSlotSeat.seatId.eq(seat.id))
        .where(
            isSameFacilityId(facilityId),
            isSameTimeSlotId(timeSlotId)
        )
        .orderBy(
            seat.rowLabel.asc(),
            seat.columnNo.asc(),
            seat.id.asc()
        )
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .fetch();

    JPAQuery<Long> count = queryFactory
        .select(timeSlotSeat.count())
        .from(timeSlotSeat)
        .join(seat)
        .on(timeSlotSeat.seatId.eq(seat.id))
        .where(
            isSameFacilityId(facilityId),
            isSameTimeSlotId(timeSlotId)
        );

    return PageableExecutionUtils.getPage(content, pageable, count::fetchOne);
  }

  private BooleanExpression isSameFacilityId(Long facilityId) {
    return seat.facilityId.eq(facilityId);
  }

  private BooleanExpression isSameTimeSlotId(Long timeSlotId) {
    return timeSlotSeat.timeSlotId.eq(timeSlotId);
  }

}
