package kr.ac.hansung.kjh.seatreservationapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reservations")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReservationEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Column(name = "user_id", nullable = false)
  private String userId;

  @NotNull
  @Enumerated(EnumType.STRING)
  @Column(name = "reservation_status", nullable = false)
  private ReservationStatus status;

  @NotNull
  @Column(name = "time_slot_seat_id", nullable = false)
  private Long timeSlotSeatId;

  @NotNull
  @Column(name = "created_at", nullable = false)
  private LocalDateTime createdAt;

  private LocalDateTime checkedInAt;
  private LocalDateTime checkedOutAt;

  @Builder
  private ReservationEntity(String userId, ReservationStatus status, Long timeSlotSeatId,
      LocalDateTime checkedInAt,
      LocalDateTime checkedOutAt) {
    this.userId = userId;
    this.status = status;
    this.timeSlotSeatId = timeSlotSeatId;
    this.checkedInAt = checkedInAt;
    this.checkedOutAt = checkedOutAt;
    this.createdAt = LocalDateTime.now();
  }

  public static ReservationEntity create(String userId, ReservationStatus status,
      Long timeSlotSeatId,
      LocalDateTime checkedInAt, LocalDateTime checkedOutAt) {
    return ReservationEntity.builder().userId(userId).status(status).timeSlotSeatId(timeSlotSeatId)
        .checkedInAt(
            checkedInAt).checkedOutAt(checkedOutAt).build();
  }
}
