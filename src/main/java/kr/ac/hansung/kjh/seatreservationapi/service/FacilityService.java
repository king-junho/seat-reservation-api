package kr.ac.hansung.kjh.seatreservationapi.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import kr.ac.hansung.kjh.seatreservationapi.dto.response.FacilitySearchResponse;
import kr.ac.hansung.kjh.seatreservationapi.dto.response.FacilitySeatsResponse;
import kr.ac.hansung.kjh.seatreservationapi.dto.response.FacilityTimeSlotsResponse;
import kr.ac.hansung.kjh.seatreservationapi.entity.FacilityEntity;
import kr.ac.hansung.kjh.seatreservationapi.entity.FacilityType;
import kr.ac.hansung.kjh.seatreservationapi.entity.Region;
import kr.ac.hansung.kjh.seatreservationapi.entity.SeatEntity;
import kr.ac.hansung.kjh.seatreservationapi.entity.TimeSlotEntity;
import kr.ac.hansung.kjh.seatreservationapi.repository.FacilityRepository;
import kr.ac.hansung.kjh.seatreservationapi.repository.SeatRepository;
import kr.ac.hansung.kjh.seatreservationapi.repository.TimeSlotRepository;
import kr.ac.hansung.kjh.seatreservationapi.repository.TimeSlotSeatRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class FacilityService {

  private static final int PAGE_SIZE = 5;
  private final FacilityRepository facilityRepository;
  private final TimeSlotRepository timeSlotRepository;
  private final SeatRepository seatRepository;
  private final TimeSlotSeatRepository timeSlotSeatRepository;

  public FacilitySearchResponse findFacilitiesByCondition(LocalDateTime dateTime, Region region,
      FacilityType type, int currentPage) {
    Pageable pageable = PageRequest.of(currentPage, PAGE_SIZE,
        Sort.by(Direction.ASC, "id"));

    Page<FacilityEntity> result = facilityRepository.findByConditions(dateTime, region,
        type, pageable);

    return FacilitySearchResponse.from(result);
  }

  public FacilityTimeSlotsResponse findFacilityTimeSlots(Long facilityId, LocalDate date,
      int currentPage) {
    Pageable pageable = PageRequest.of(currentPage, PAGE_SIZE,
        Sort.by(Direction.ASC, "startTime"));

    LocalDateTime from = date.atStartOfDay();
    LocalDateTime to = date.plusDays(1).atStartOfDay();

    Page<TimeSlotEntity> result = timeSlotRepository.findByFacilityIdAndStartTimeGreaterThanEqualAndStartTimeLessThan(
        facilityId,
        from, to, pageable);

    //int totalSeats = timeSlotSeatRepository.countBy
    //int availableSeats =

    return FacilityTimeSlotsResponse.from(result);
  }

  public FacilitySeatsResponse findFacilitySeats(Long facilityId, int currentPage) {
    Pageable pageable = PageRequest.of(currentPage, PAGE_SIZE,
        Sort.by(Direction.ASC, "rowLabel", "columnNo"));
    Page<SeatEntity> result = seatRepository.findByFacilityId(facilityId, pageable);

    return FacilitySeatsResponse.from(result);
  }
}
