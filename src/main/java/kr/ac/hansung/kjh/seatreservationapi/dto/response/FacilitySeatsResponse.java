package kr.ac.hansung.kjh.seatreservationapi.dto.response;

import java.util.List;
import kr.ac.hansung.kjh.seatreservationapi.entity.SeatEntity;
import org.springframework.data.domain.Page;

public record FacilitySeatsResponse(
    List<SeatResponse> content,
    int page,
    int size,
    long totalElement,
    int totalPages
) {

  public static FacilitySeatsResponse from(Page<SeatEntity> result) {
    List<SeatResponse> content = result.getContent().stream().map(SeatResponse::from).toList();

    return new FacilitySeatsResponse(
        content, result.getNumber(), result.getSize(), result.getTotalElements(),
        result.getTotalPages()
    );
  }

}
