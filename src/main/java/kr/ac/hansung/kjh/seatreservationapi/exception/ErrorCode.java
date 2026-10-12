package kr.ac.hansung.kjh.seatreservationapi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    // 400 BAD_REQUEST: 잘못된 요청
    INVALID_TIME_RANGE(HttpStatus.BAD_REQUEST, "종료 시간은 시작 시간보다 빠를 수 없습니다.");

    private final HttpStatus status;
    private final String message;
}
