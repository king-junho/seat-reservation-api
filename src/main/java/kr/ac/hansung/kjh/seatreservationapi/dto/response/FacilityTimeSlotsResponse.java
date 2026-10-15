package kr.ac.hansung.kjh.seatreservationapi.dto.response;

import java.util.List;
import kr.ac.hansung.kjh.seatreservationapi.entity.TimeSlotEntity;
import org.springframework.data.domain.Page;

public record FacilityTimeSlotsResponse(
    List<TimeSlotResponse> content,
    int page,
    int size,
    long totalElement,
    int totalPages
) {

  public static FacilityTimeSlotsResponse from(Page<TimeSlotEntity> result) {
    List<TimeSlotResponse> content = result.getContent().stream().map(TimeSlotResponse::from)
        .toList();

    return new FacilityTimeSlotsResponse(content, result.getNumber(), result.getSize(),
        result.getTotalElements(), result.getTotalPages());
  }
}
