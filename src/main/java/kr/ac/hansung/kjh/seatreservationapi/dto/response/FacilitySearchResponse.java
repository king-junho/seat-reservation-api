package kr.ac.hansung.kjh.seatreservationapi.dto.response;

import java.util.List;
import kr.ac.hansung.kjh.seatreservationapi.entity.FacilityEntity;
import org.springframework.data.domain.Page;

public record FacilitySearchResponse(
    List<FacilityResponse> content, int page, int size, long totalElement, int totalPages
) {

  public static FacilitySearchResponse from(Page<FacilityEntity> result) {
    List<FacilityResponse> content = result.getContent().stream().map(FacilityResponse::from)
        .toList();

    return new FacilitySearchResponse(content, result.getNumber(), result.getSize(),
        result.getTotalElements(), result.getTotalPages());
  }
}
