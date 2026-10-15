package kr.ac.hansung.kjh.seatreservationapi.controller;

import jakarta.validation.Valid;
import java.security.Principal;
import kr.ac.hansung.kjh.seatreservationapi.dto.request.ReservationRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ReservationController {
  //private final RedisService redisService;

  // 예약 신청
  @PostMapping
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void requestReservation(@Valid @RequestBody ReservationRequest request,
      Principal principal) {
    String userEmail = principal.getName();
    Long timeSlotId = request.timeSlotId();
    Long seatId = request.seatId();

    //redisService.addQueue(userEmail);

    //reservationService.reserveSeat(seatId, timeSlotId);
  }
}
