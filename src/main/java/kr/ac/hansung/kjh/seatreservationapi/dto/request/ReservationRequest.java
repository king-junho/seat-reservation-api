package kr.ac.hansung.kjh.seatreservationapi.dto.request;

import jakarta.validation.constraints.NotNull;

public record ReservationRequest(
    @NotNull
    Long timeSlotId,

    @NotNull
    Long seatId
) {

}
