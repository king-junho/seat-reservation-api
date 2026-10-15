package kr.ac.hansung.kjh.seatreservationapi.service;

import org.springframework.stereotype.Service;

@Service
public class ReservationService {

  public void reserveSeat(Long seatId, Long timeSlotId) {
    //예약할 때 여기서
    //redis로 대기열로 보내고 순차처리
    //여기서 처리해야 할 건
    //예약 버튼 누르면 먼저 대기열 보내기 -> 대기열에서 본인 순서가 되면 MQ로 api 호출해서 예약 처리하기
    //그럼 controller에서 먼저 보내야 되는 곳은 RedisService로 먼저 보내고 RedisService에서 스케줄러 돌리고
    //스케줄러 돌려서 나온 값이 MQ로 reserve처리 하면 되는데
    //reserve 처리는 reserve 데이터 기록 남기고, 좌석 예매 처리 하고(점유 정보 업데이트)
    
  }
}
