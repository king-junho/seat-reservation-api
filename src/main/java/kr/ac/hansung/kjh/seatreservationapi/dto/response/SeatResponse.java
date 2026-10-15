package kr.ac.hansung.kjh.seatreservationapi.dto.response;

import kr.ac.hansung.kjh.seatreservationapi.entity.SeatEntity;

public record SeatResponse(
    Long id,
    Long facilityId,
    String rowLabel,
    int columnNo
) {

  public static SeatResponse from(SeatEntity seat) {
    return new SeatResponse(
        seat.getId(),
        seat.getFacilityId(),
        seat.getRowLabel(),
        seat.getColumnNo()
    );
  }
}
