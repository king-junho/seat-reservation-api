package kr.ac.hansung.kjh.seatreservationapi.dto.response;

import kr.ac.hansung.kjh.seatreservationapi.entity.FacilityEntity;
import kr.ac.hansung.kjh.seatreservationapi.entity.FacilityType;
import kr.ac.hansung.kjh.seatreservationapi.entity.Region;

public record FacilityResponse(
    String name,
    Region region,
    FacilityType type
) {

  public static FacilityResponse from(FacilityEntity facility) {
    return new FacilityResponse(facility.getName(), facility.getRegion(), facility.getType());
  }

}
