package kr.ac.hansung.kjh.seatreservationapi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
  // 400 BAD_REQUEST: 잘못된 요청
  INVALID_TIME_RANGE(HttpStatus.BAD_REQUEST, "종료 시간은 시작 시간보다 빠를 수 없습니다."),
  INVALID_DATE(HttpStatus.BAD_REQUEST, "올바른 날짜가 지정되지 않았습니다."),

  // 401 UNAUTHORIZED: 잘못된 자격 증명
  INVALID_CREDENTIALS(HttpStatus.UNAUTHORIZED, "아이디나 비밀번호가 일치하지 않습니다."),

  // 409 CONFLICT: 현재 리소스 상태와 요청 충돌
  DUPLICATE_EMAIL(HttpStatus.CONFLICT, "이미 가입된 이메일입니다.");

  private final HttpStatus status;
  private final String message;
}
