package kr.ac.hansung.kjh.seatreservationapi.repository;

import kr.ac.hansung.kjh.seatreservationapi.entity.TimeSlotEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeSlotRepository extends CustomTimeSlotRepository,
    JpaRepository<TimeSlotEntity, Long> {

  boolean existsByIdAndFacilityId(Long id, Long facilityId);
}
