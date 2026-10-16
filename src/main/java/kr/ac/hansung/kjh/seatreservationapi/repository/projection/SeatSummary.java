package kr.ac.hansung.kjh.seatreservationapi.repository.projection;

import com.querydsl.core.annotations.QueryProjection;

public record SeatSummary(
    Long id,
    Long facilityId,
    String rowLabel,
    Integer columnNo,
    boolean isReservation
) {

  @QueryProjection
  public SeatSummary {

  }

}
