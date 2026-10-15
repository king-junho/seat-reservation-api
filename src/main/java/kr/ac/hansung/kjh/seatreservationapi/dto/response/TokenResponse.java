package kr.ac.hansung.kjh.seatreservationapi.dto.response;

import jakarta.validation.constraints.NotBlank;

public record TokenResponse(
    @NotBlank
    String accessToken) {

}
