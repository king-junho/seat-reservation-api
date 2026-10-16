package kr.ac.hansung.kjh.seatreservationapi.dto.response;

import java.util.List;
import org.springframework.data.domain.Page;

public record FacilityTimeSlotsResponse(
    List<TimeSlotResponse> content,
    int page,
    int size,
    long totalElement,
    int totalPages
) {

  public static FacilityTimeSlotsResponse from(Page<TimeSlotResponse> result) {
    
    return new FacilityTimeSlotsResponse(result.getContent(), result.getNumber(), result.getSize(),
        result.getTotalElements(), result.getTotalPages());
  }
}
