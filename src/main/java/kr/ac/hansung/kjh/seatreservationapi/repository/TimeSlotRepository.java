package kr.ac.hansung.kjh.seatreservationapi.repository;

import java.time.LocalDateTime;
import kr.ac.hansung.kjh.seatreservationapi.entity.TimeSlotEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeSlotRepository extends JpaRepository<TimeSlotEntity, Long> {

  Page<TimeSlotEntity> findByFacilityIdAndStartTimeGreaterThanEqualAndStartTimeLessThan(
      Long facilityId, LocalDateTime from, LocalDateTime to,
      Pageable pageable);
}
