package kr.ac.hansung.kjh.seatreservationapi.dto.request;

import jakarta.validation.constraints.Min;
import java.time.LocalDateTime;
import kr.ac.hansung.kjh.seatreservationapi.entity.FacilityType;
import kr.ac.hansung.kjh.seatreservationapi.entity.Region;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public record SearchFacilityRequest(
    @DateTimeFormat(iso = ISO.DATE_TIME)
    LocalDateTime dateTime,

    Region region,

    FacilityType type,

    @Min(0)
    Integer currentPage
) {

  public SearchFacilityRequest {
    if (currentPage == null) {
      currentPage = 0;
    }
  }
}
