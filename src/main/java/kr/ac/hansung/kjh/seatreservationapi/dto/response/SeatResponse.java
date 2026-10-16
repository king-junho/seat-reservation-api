package kr.ac.hansung.kjh.seatreservationapi.dto.response;

import kr.ac.hansung.kjh.seatreservationapi.repository.projection.SeatSummary;

public record SeatResponse(
    Long id,
    Long facilityId,
    String rowLabel,
    Integer columnNo,
    Boolean isReservation
) {

  public static SeatResponse from(SeatSummary seat) {
    return new SeatResponse(
        seat.id(),
        seat.facilityId(),
        seat.rowLabel(),
        seat.columnNo(),
        seat.isReservation()
    );
  }
}
