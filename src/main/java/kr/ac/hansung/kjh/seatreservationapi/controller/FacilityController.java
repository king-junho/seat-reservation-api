package kr.ac.hansung.kjh.seatreservationapi.controller;

import java.time.LocalDateTime;
import kr.ac.hansung.kjh.seatreservationapi.dto.request.SearchFacilityRequest;
import kr.ac.hansung.kjh.seatreservationapi.dto.response.FacilitySearchResponse;
import kr.ac.hansung.kjh.seatreservationapi.entity.FacilityType;
import kr.ac.hansung.kjh.seatreservationapi.entity.Region;
import kr.ac.hansung.kjh.seatreservationapi.service.FacilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
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
  public FacilitySearchResponse searchFacilities(SearchFacilityRequest request) {
    LocalDateTime dateTime = request.dateTime();
    Region region = request.region();
    FacilityType type = request.type();
    int currentPage = request.currentPage();

    return facilityService.findFacilitiesByCondition(dateTime, region, type, currentPage);
  }

  @GetMapping("/search/{facilityId}/timeslots")
  public void searchTimeSlots(@PathVariable Long facilityId) {
    System.out.println("searchTimeSlots Function : " + facilityId);
  }

  @GetMapping("/{facilityId}/seats")
  public void searchSeats(@PathVariable Long facilityId) {
    System.out.println("searchSeats Function : " + facilityId);
  }
}
