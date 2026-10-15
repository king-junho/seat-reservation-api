package kr.ac.hansung.kjh.seatreservationapi.repository;

import kr.ac.hansung.kjh.seatreservationapi.entity.TimeSlotSeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeSlotSeatRepository extends JpaRepository<TimeSlotSeatEntity, Long> {

}
