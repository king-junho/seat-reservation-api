package kr.ac.hansung.kjh.seatreservationapi.controller;

import jakarta.validation.Valid;
import java.security.Principal;
import kr.ac.hansung.kjh.seatreservationapi.dto.request.ReservationRequest;
import kr.ac.hansung.kjh.seatreservationapi.service.RedisService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ReservationController {

  private final RedisService redisService;


  @PostMapping("facilities/{facilityId}/timeslots/{timeSlotId}/queue-entries")
  public Long registerQueueEntry(@PathVariable Long facilityId, @PathVariable Long timeSlotId,
      Principal principal) {
    return redisService.registerQueue(facilityId, timeSlotId, principal.getName());
  }

  @PostMapping
  public void requestReservation(@Valid @RequestBody ReservationRequest request,
      Principal principal) {
    String userEmail = principal.getName();
    Long timeSlotId = request.timeSlotId();
    Long seatId = request.seatId();

    //redisService.addQueue(userEmail);

    //reservationService.reserveSeat(seatId, timeSlotId);
  }
}
