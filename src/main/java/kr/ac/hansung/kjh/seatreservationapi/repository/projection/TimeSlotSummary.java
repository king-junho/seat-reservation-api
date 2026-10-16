package kr.ac.hansung.kjh.seatreservationapi.repository.projection;

import com.querydsl.core.annotations.QueryProjection;
import java.time.LocalDateTime;

public record TimeSlotSummary(
    Long id,
    Long facilityId,
    LocalDateTime startTime,
    LocalDateTime endTime,
    long totalSeats,
    long availableSeats
) {

  @QueryProjection
  public TimeSlotSummary {
  }
}
