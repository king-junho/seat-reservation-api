package kr.ac.hansung.kjh.seatreservationapi.repository;

import java.time.LocalDateTime;
import kr.ac.hansung.kjh.seatreservationapi.dto.response.TimeSlotResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomTimeSlotRepository {

  Page<TimeSlotResponse> findByFacilityIdAndLocalDate(Long facilityId, LocalDateTime from,
      LocalDateTime to, Pageable pageable);

}
