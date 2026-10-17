package kr.ac.hansung.kjh.seatreservationapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisService {

  private final StringRedisTemplate stringRedisTemplate;

  public Long registerQueue(Long facilityId, Long timeSlotId, String userId) {
    String key = String.format("queue:facility:%d:timeslot:%d:waiting", facilityId, timeSlotId);
    long now = System.currentTimeMillis();

    stringRedisTemplate.opsForZSet().addIfAbsent(key, userId, now);

    Long rank = stringRedisTemplate.opsForZSet().rank(key, userId);

    return rank != null ? rank : -1;
  }

}
