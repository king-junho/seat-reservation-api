package kr.ac.hansung.kjh.seatreservationapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import kr.ac.hansung.kjh.seatreservationapi.exception.CustomException;
import kr.ac.hansung.kjh.seatreservationapi.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "time_slots")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TimeSlotEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Column(name = "facility_id", nullable = false)
  private Long facilityId;

  @NotNull
  @Column(name = "start_time", nullable = false)
  private LocalDateTime startTime;

  @NotNull
  @Column(name = "end_time", nullable = false)
  private LocalDateTime endTime;

  
  @Builder
  private TimeSlotEntity(Long facilityId, LocalDateTime startTime, LocalDateTime endTime) {
    if (facilityId == null || facilityId <= 0) {
      throw new CustomException(
          ErrorCode.INVALID_FACILITY_ID
      );
    }

    if (startTime == null || endTime == null) {
      throw new CustomException(
          ErrorCode.REQUIRED_TIME_MISSING
      );
    }

    if (!startTime.isBefore(endTime)) {
      throw new CustomException(
          ErrorCode.INVALID_TIME_RANGE
      );
    }

    this.facilityId = facilityId;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  public static TimeSlotEntity create(Long facilityId, LocalDateTime startTime,
      LocalDateTime endTime) {
    return TimeSlotEntity.builder().facilityId(facilityId).startTime(startTime).endTime(endTime)
        .build();
  }
}
