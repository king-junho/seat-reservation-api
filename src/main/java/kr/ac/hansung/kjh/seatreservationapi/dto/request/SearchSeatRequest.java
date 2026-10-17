package kr.ac.hansung.kjh.seatreservationapi.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record SearchSeatRequest(
    @NotNull
    Long timeSlotId,

    @Min(0)
    Integer currentPage
) {

  public SearchSeatRequest {
    if (currentPage == null) {
      currentPage = 0;
    }
  }
}
