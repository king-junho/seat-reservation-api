package kr.ac.hansung.kjh.seatreservationapi.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class RedisScheduler {

  @Scheduled(fixedDelay = 1000)
  private void processQueue() {
    // 잔여 좌석 확인
    // 현재 입장 가능한 인원 수 확인
    // 들여보낼 수 있는 인원 차례대로 대기열 입장
    // 입장한 인원은 대기열에서 삭제
  }
}
