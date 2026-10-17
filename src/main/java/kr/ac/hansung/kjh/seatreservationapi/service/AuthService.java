package kr.ac.hansung.kjh.seatreservationapi.service;

import kr.ac.hansung.kjh.seatreservationapi.exception.CustomException;
import kr.ac.hansung.kjh.seatreservationapi.exception.ErrorCode;
import kr.ac.hansung.kjh.seatreservationapi.security.JwtTokenService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthService {

  private final AuthenticationManager authenticationManager;
  private final JwtTokenService jwtTokenService;

  public String login(String email, String password) {
    Authentication authentication;

    try {
      authentication = authenticationManager.authenticate(
          UsernamePasswordAuthenticationToken.unauthenticated(email, password));
    } catch (BadCredentialsException e) {
      throw new CustomException(ErrorCode.INVALID_CREDENTIALS);
    }
    
    return jwtTokenService.createToken(authentication.getName());
  }
}
