package kr.ac.hansung.kjh.seatreservationapi.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import java.time.LocalDateTime;

public record TimeSlotResponse(
    Long id,
    Long facilityId,
    LocalDateTime startTime,
    LocalDateTime endTime,
    int totalSeats,
    int availableSeats
) {

  @QueryProjection
  public TimeSlotResponse(Long id, Long facilityId, LocalDateTime startTime, LocalDateTime endTime,
      int totalSeats, int availableSeats) {
    this.id = id;
    this.facilityId = facilityId;
    this.startTime = startTime;
    this.endTime = endTime;
    this.totalSeats = totalSeats;
    this.availableSeats = availableSeats;
  }
}
