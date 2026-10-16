package kr.ac.hansung.kjh.seatreservationapi.dto.response;

import java.time.LocalDateTime;
import kr.ac.hansung.kjh.seatreservationapi.repository.projection.TimeSlotSummary;

public record TimeSlotResponse(
    Long id,
    Long facilityId,
    LocalDateTime startTime,
    LocalDateTime endTime,
    long totalSeats,
    long availableSeats
) {

  public static TimeSlotResponse from(TimeSlotSummary timeSlotSummary) {
    return new TimeSlotResponse(
        timeSlotSummary.id(),
        timeSlotSummary.facilityId(),
        timeSlotSummary.startTime(),
        timeSlotSummary.endTime(),
        timeSlotSummary.totalSeats(),
        timeSlotSummary.availableSeats()
    );
  }
}
