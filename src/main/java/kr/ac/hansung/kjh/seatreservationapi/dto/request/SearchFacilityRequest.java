package kr.ac.hansung.kjh.seatreservationapi.dto.request;

import java.time.LocalDateTime;
import kr.ac.hansung.kjh.seatreservationapi.entity.FacilityType;
import kr.ac.hansung.kjh.seatreservationapi.entity.Region;

public record SearchFacilityRequest(
    LocalDateTime dateTime,

    Region region,

    FacilityType type,


    Integer currentPage
) {

  public SearchFacilityRequest {
    if (currentPage == null) {
      currentPage = 0;
    }
  }
}
