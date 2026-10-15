package kr.ac.hansung.kjh.seatreservationapi.repository;

import kr.ac.hansung.kjh.seatreservationapi.entity.FacilityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacilityRepository extends JpaRepository<FacilityEntity, Long>,
    CustomFacilityRepository {

}
