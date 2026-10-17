package kr.ac.hansung.kjh.seatreservationapi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
  // 400 BAD_REQUEST: 잘못된 요청
  INVALID_DATE(HttpStatus.BAD_REQUEST, "올바른 날짜가 지정되지 않았습니다."),
  INVALID_FACILITY_ID(HttpStatus.BAD_REQUEST, "시설 ID는 양수여야 합니다."),
  REQUIRED_TIME_MISSING(HttpStatus.BAD_REQUEST, "시작 시간과 종료 시간은 필수입니다."),
  INVALID_TIME_RANGE(HttpStatus.BAD_REQUEST, "종료 시간은 시작 시간보다 늦어야 합니다."),

  // 401 UNAUTHORIZED: 잘못된 자격 증명
  INVALID_CREDENTIALS(HttpStatus.UNAUTHORIZED, "아이디나 비밀번호가 일치하지 않습니다."),

  // 404 NOT_FOUND: 리소스를 찾을 수 없음
  FACILITY_NOT_FOUND(HttpStatus.NOT_FOUND, "시설을 찾을 수 없습니다."),
  TIME_SLOT_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 시설의 시간대를 찾을 수 없습니다."),

  // 409 CONFLICT: 현재 리소스 상태와 요청 충돌
  DUPLICATE_EMAIL(HttpStatus.CONFLICT, "이미 가입된 이메일입니다.");

  private final HttpStatus status;
  private final String message;
}
