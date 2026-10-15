package kr.ac.hansung.kjh.seatreservationapi.controller;

import jakarta.validation.Valid;
import kr.ac.hansung.kjh.seatreservationapi.dto.request.LoginRequest;
import kr.ac.hansung.kjh.seatreservationapi.dto.request.SignupRequest;
import kr.ac.hansung.kjh.seatreservationapi.dto.response.TokenResponse;
import kr.ac.hansung.kjh.seatreservationapi.service.AuthService;
import kr.ac.hansung.kjh.seatreservationapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;
  private final AuthService authService;

  @PostMapping("/login")
  @ResponseStatus(HttpStatus.CREATED)
  public TokenResponse login(@Valid @RequestBody LoginRequest request) {
    String email = request.email();
    String password = request.password();

    String accessToken = authService.login(email, password);

    return new TokenResponse(accessToken);
  }

  @PostMapping("/signup")
  public void signup(@Valid @RequestBody SignupRequest request) {
    String email = request.email();
    String password = request.password();
    String name = request.name();

    userService.signup(email, password, name);
  }
}
