package kr.ac.hansung.kjh.seatreservationapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(
        name = "time_slot_seats",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_time_slot_seat",
                columnNames = {"time_slot_id","seat_id"}
        )
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TimeSlotSeatEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "seat_id", nullable = false)
    private Long seatId;

    @NotNull
    @Column(name = "time_slot_id",nullable = false)
    private Long timeSlotId;

    @Column(name="current_reservation_id")
    private Long currentReservationId;

    @Builder
    private TimeSlotSeatEntity(Long seatId, Long timeSlotId, Long currentReservationId) {
        this.seatId = seatId;
        this.timeSlotId = timeSlotId;
        this.currentReservationId = currentReservationId;
    }

    public static TimeSlotSeatEntity create(Long seatId, Long timeSlotId, Long currentReservationId) {
        return TimeSlotSeatEntity.builder()
                .seatId(seatId)
                .timeSlotId(timeSlotId)
                .currentReservationId(currentReservationId)
                .build();
    }
}
