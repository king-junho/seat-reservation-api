package kr.ac.hansung.kjh.seatreservationapi.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

public record SearchTimeSlotRequest(
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate date,

    @Min(0)
    Integer currentPage
) {

  public SearchTimeSlotRequest {
    if (currentPage == null) {
      currentPage = 0;
    }
  }
}
