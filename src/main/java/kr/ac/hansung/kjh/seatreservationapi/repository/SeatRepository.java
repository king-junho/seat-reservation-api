package kr.ac.hansung.kjh.seatreservationapi.repository;

import kr.ac.hansung.kjh.seatreservationapi.entity.SeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<SeatEntity, Long>, CustomSeatRepository {

}
