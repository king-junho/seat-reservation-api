package kr.ac.hansung.kjh.seatreservationapi.service;

import java.time.LocalDateTime;
import kr.ac.hansung.kjh.seatreservationapi.dto.response.FacilitySearchResponse;
import kr.ac.hansung.kjh.seatreservationapi.entity.FacilityEntity;
import kr.ac.hansung.kjh.seatreservationapi.entity.FacilityType;
import kr.ac.hansung.kjh.seatreservationapi.entity.Region;
import kr.ac.hansung.kjh.seatreservationapi.repository.FacilityRepository;
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

  public FacilitySearchResponse findFacilitiesByCondition(LocalDateTime dateTime, Region region,
      FacilityType type, int currentPage) {
    Pageable pageable = PageRequest.of(currentPage, PAGE_SIZE,
        Sort.by(Direction.ASC, "id"));

    Page<FacilityEntity> result = facilityRepository.findByConditions(dateTime, region,
        type, pageable);

    return FacilitySearchResponse.from(result);
  }
}
