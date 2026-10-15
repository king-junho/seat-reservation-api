package kr.ac.hansung.kjh.seatreservationapi.service;

import kr.ac.hansung.kjh.seatreservationapi.entity.UserEntity;
import kr.ac.hansung.kjh.seatreservationapi.exception.CustomException;
import kr.ac.hansung.kjh.seatreservationapi.exception.ErrorCode;
import kr.ac.hansung.kjh.seatreservationapi.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder encoder;

  public void signup(String email, String password, String name) {
    if (userRepository.existsById(email)) {
      throw new CustomException(ErrorCode.DUPLICATE_EMAIL);
    }

    String hashedPassword = encoder.encode(password);
    UserEntity user = UserEntity.createUser(email, hashedPassword, name);

    userRepository.save(user);
  }
}
