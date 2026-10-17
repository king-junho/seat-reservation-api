package kr.ac.hansung.kjh.seatreservationapi.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import kr.ac.hansung.kjh.seatreservationapi.dto.response.FacilitySearchResponse;
import kr.ac.hansung.kjh.seatreservationapi.dto.response.FacilitySeatsResponse;
import kr.ac.hansung.kjh.seatreservationapi.dto.response.FacilityTimeSlotsResponse;
import kr.ac.hansung.kjh.seatreservationapi.entity.FacilityEntity;
import kr.ac.hansung.kjh.seatreservationapi.entity.FacilityType;
import kr.ac.hansung.kjh.seatreservationapi.entity.Region;
import kr.ac.hansung.kjh.seatreservationapi.exception.CustomException;
import kr.ac.hansung.kjh.seatreservationapi.exception.ErrorCode;
import kr.ac.hansung.kjh.seatreservationapi.repository.FacilityRepository;
import kr.ac.hansung.kjh.seatreservationapi.repository.SeatRepository;
import kr.ac.hansung.kjh.seatreservationapi.repository.TimeSlotRepository;
import kr.ac.hansung.kjh.seatreservationapi.repository.projection.SeatSummary;
import kr.ac.hansung.kjh.seatreservationapi.repository.projection.TimeSlotSummary;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class FacilityService {

  private static final int PAGE_SIZE = 5;
  private final FacilityRepository facilityRepository;
  private final TimeSlotRepository timeSlotRepository;
  private final SeatRepository seatRepository;

  public FacilitySearchResponse findFacilitiesByCondition(LocalDateTime dateTime, Region region,
      FacilityType type, int currentPage) {
    Pageable pageable = PageRequest.of(currentPage, PAGE_SIZE);

    Page<FacilityEntity> result = facilityRepository.findByConditions(dateTime, region,
        type, pageable);

    return FacilitySearchResponse.from(result);
  }

  public FacilityTimeSlotsResponse findFacilityTimeSlots(Long facilityId, LocalDate date,
      int currentPage) {
    validateFacilityExists(facilityId);

    Pageable pageable = PageRequest.of(currentPage, PAGE_SIZE);
    LocalDateTime from = date.atStartOfDay();
    LocalDateTime to = date.plusDays(1).atStartOfDay();

    Page<TimeSlotSummary> result = timeSlotRepository.findByFacilityIdAndLocalDate(
        facilityId, from, to, pageable
    );

    return FacilityTimeSlotsResponse.from(result);
  }

  public FacilitySeatsResponse findFacilitySeats(Long facilityId, long timeSlotId,
      int currentPage) {
    validateFacilityExists(facilityId);
    validateTimeSlotExists(timeSlotId, facilityId);
    
    Pageable pageable = PageRequest.of(currentPage, PAGE_SIZE);

    Page<SeatSummary> result = seatRepository.findByFacilityIdAndTimeSlotId(facilityId, timeSlotId,
        pageable);

    return FacilitySeatsResponse.from(result);
  }

  private void validateFacilityExists(Long facilityId) {
    if (!facilityRepository.existsById(facilityId)) {
      throw new CustomException(ErrorCode.FACILITY_NOT_FOUND);
    }
  }

  private void validateTimeSlotExists(Long timeSlotId, Long facilityId) {
    if (!timeSlotRepository.existsByIdAndFacilityId(timeSlotId, facilityId)) {
      throw new CustomException(ErrorCode.TIME_SLOT_NOT_FOUND);
    }
  }
}
