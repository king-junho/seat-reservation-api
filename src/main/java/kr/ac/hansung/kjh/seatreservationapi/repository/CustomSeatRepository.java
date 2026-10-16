package kr.ac.hansung.kjh.seatreservationapi.repository;

import kr.ac.hansung.kjh.seatreservationapi.repository.projection.SeatSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomSeatRepository {

  Page<SeatSummary> findByFacilityIdAndTimeSlotId(Long facilityId, Long timeSlotId,
      Pageable pageable);
}
