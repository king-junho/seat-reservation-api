package kr.ac.hansung.kjh.seatreservationapi.controller;

import jakarta.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import kr.ac.hansung.kjh.seatreservationapi.dto.request.SearchFacilityRequest;
import kr.ac.hansung.kjh.seatreservationapi.dto.request.SearchSeatRequest;
import kr.ac.hansung.kjh.seatreservationapi.dto.request.SearchTimeSlotRequest;
import kr.ac.hansung.kjh.seatreservationapi.dto.response.FacilitySearchResponse;
import kr.ac.hansung.kjh.seatreservationapi.dto.response.FacilitySeatsResponse;
import kr.ac.hansung.kjh.seatreservationapi.dto.response.FacilityTimeSlotsResponse;
import kr.ac.hansung.kjh.seatreservationapi.entity.FacilityType;
import kr.ac.hansung.kjh.seatreservationapi.entity.Region;
import kr.ac.hansung.kjh.seatreservationapi.service.FacilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/facilities")
@RequiredArgsConstructor
public class FacilityController {

  private final FacilityService facilityService;

  // 시설 검색
  @GetMapping("/search")
  public FacilitySearchResponse searchFacilities(
      @Valid @ModelAttribute SearchFacilityRequest request) {
    LocalDateTime dateTime = request.dateTime();
    Region region = request.region();
    FacilityType type = request.type();
    int currentPage = request.currentPage();

    return facilityService.findFacilitiesByCondition(dateTime, region, type, currentPage);
  }

  @GetMapping("/{facilityId}/timeslots")
  public FacilityTimeSlotsResponse searchTimeSlots(@PathVariable Long facilityId,
      @Valid @ModelAttribute SearchTimeSlotRequest request) {
    LocalDate date = request.date();
    int currentPage = request.currentPage();

    return facilityService.findFacilityTimeSlots(facilityId, date, currentPage);
  }

  @GetMapping("/{facilityId}/seats")
  public FacilitySeatsResponse searchSeats(@PathVariable Long facilityId,
      @Valid @ModelAttribute SearchSeatRequest request) {
    long timeSlotId = request.timeSlotId();
    int currentPage = request.currentPage();

    return facilityService.findFacilitySeats(facilityId, timeSlotId, currentPage);
  }
}
