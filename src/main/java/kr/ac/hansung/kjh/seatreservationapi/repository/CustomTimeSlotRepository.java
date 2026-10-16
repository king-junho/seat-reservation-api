package kr.ac.hansung.kjh.seatreservationapi.repository;

import java.time.LocalDateTime;
import kr.ac.hansung.kjh.seatreservationapi.repository.projection.TimeSlotSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomTimeSlotRepository {

  Page<TimeSlotSummary> findByFacilityIdAndLocalDate(Long facilityId, LocalDateTime from,
      LocalDateTime to, Pageable pageable);

}
