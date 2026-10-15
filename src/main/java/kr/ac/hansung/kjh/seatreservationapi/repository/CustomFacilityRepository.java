package kr.ac.hansung.kjh.seatreservationapi.repository;

import java.time.LocalDateTime;
import kr.ac.hansung.kjh.seatreservationapi.entity.FacilityEntity;
import kr.ac.hansung.kjh.seatreservationapi.entity.FacilityType;
import kr.ac.hansung.kjh.seatreservationapi.entity.Region;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomFacilityRepository {

  Page<FacilityEntity> findByConditions(LocalDateTime dateTime, Region region,
      FacilityType type, Pageable pageable);

}
