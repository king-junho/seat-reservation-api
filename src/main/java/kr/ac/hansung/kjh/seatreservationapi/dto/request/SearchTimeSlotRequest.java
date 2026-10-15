package kr.ac.hansung.kjh.seatreservationapi.dto.request;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record SearchTimeSlotRequest(
    @NotNull
    LocalDate date,
    Integer currentPage
) {

  public SearchTimeSlotRequest {
    if (currentPage == null) {
      currentPage = 0;
    }
  }

}
