package kr.ac.hansung.kjh.seatreservationapi.service;

import java.time.Instant;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RedisService {

  private static final String QUEUE_KEY_PREFIX = "queue:event:";
  private static final long FIRST_ELEMENT = 0;
  private static final long LAST_ELEMENT = -1;
  private static final long PUBLISH_SIZE = 10;
  private static final long LAST_INDEX = 1;

  private final StringRedisTemplate stringRedisTemplate;
  private final RedisTemplate<Object, Object> redisTemplate;


  public void saveData(String key, String value) {
    stringRedisTemplate.opsForZSet().add(key, value, 1L);
  }

  public String getData(String key) {
    return stringRedisTemplate.opsForValue().get(key);
  }

  public void deleteData(String key) {
    stringRedisTemplate.delete(key);
  }

  public void addQueue(String email) {
    long now = Instant.now().toEpochMilli();

    stringRedisTemplate.opsForZSet().add(QUEUE_KEY_PREFIX, email, (double) now);
  }

  public Long getWaitingOrder(String userId) {
    Long rank = stringRedisTemplate.opsForZSet().rank(QUEUE_KEY_PREFIX, userId);
    return (rank != null) ? rank + 1 : null;
  }

  public void getOrder() {
    final long start = FIRST_ELEMENT;
    final long end = LAST_ELEMENT;

    Set<Object> queue = redisTemplate.opsForZSet().range("queue", start, end);

    for (Object people : queue) {
      Long rank = redisTemplate.opsForZSet().rank("queue", people);
      log.info("'{}'님의 현재 대기열은 {}명 남았습니다.", people, rank);
    }
  }

  public void publish() {
    final long start = FIRST_ELEMENT;
    final long end = PUBLISH_SIZE - LAST_INDEX;

    Set<Object> queue = redisTemplate.opsForZSet().range("queue", start, end);
    for (Object people : queue) {
//      redisTemplate.opsForZSet().remove(event.toString(), people);
//      this.eventCount.decrease();
    }
  }

}
