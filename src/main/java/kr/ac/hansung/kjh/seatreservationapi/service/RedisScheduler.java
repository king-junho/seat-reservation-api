package kr.ac.hansung.kjh.seatreservationapi.service;

import java.util.Set;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class RedisScheduler {

  private static final String QUEUE_KEY_PREFIX = "queue:event:";
  private final RedisService redisService;
  private final StringRedisTemplate stringRedisTemplate;
  private final ReservationService reservationService;

  @Scheduled(fixedDelay = 1000)
  private void processQueue() {
    Set<String> users = stringRedisTemplate.opsForZSet().range(QUEUE_KEY_PREFIX, 0, 29);

    if (users != null && !users.isEmpty()) {
      for (String userId : users) {
        // 1. 자리 예매 처리 시작(reservationService에서 잔여 좌석 여부 확인)
        //reservationService.reserveSeat();

        // 2. 처리가 완료된 유저는 대기열(ZSet)에서 삭제
        stringRedisTemplate.opsForZSet().remove(QUEUE_KEY_PREFIX, userId);
      }
    }
  }
}
