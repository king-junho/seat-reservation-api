package kr.ac.hansung.kjh.seatreservationapi.dto.response;

import kr.ac.hansung.kjh.seatreservationapi.entity.FacilityEntity;
import kr.ac.hansung.kjh.seatreservationapi.entity.FacilityType;
import kr.ac.hansung.kjh.seatreservationapi.entity.Region;

public record FacilityResponse(
    Long id,
    String name,
    Region region,
    FacilityType type
) {

  public static FacilityResponse from(FacilityEntity facility) {
    return new FacilityResponse(facility.getId(), facility.getName(), facility.getRegion(),
        facility.getType());
  }

}
