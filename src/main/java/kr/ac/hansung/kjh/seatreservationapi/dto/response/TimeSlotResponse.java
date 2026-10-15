package kr.ac.hansung.kjh.seatreservationapi.dto.response;

import java.time.LocalDateTime;
import kr.ac.hansung.kjh.seatreservationapi.entity.TimeSlotEntity;

public record TimeSlotResponse(
    Long id,
    Long facilityId,
    LocalDateTime startTime,
    LocalDateTime endTime
) {

  public static TimeSlotResponse from(TimeSlotEntity timeSlot) {
    return new TimeSlotResponse(
        timeSlot.getId(),
        timeSlot.getFacilityId(),
        timeSlot.getStartTime(),
        timeSlot.getEndTime()
    );
  }
}
