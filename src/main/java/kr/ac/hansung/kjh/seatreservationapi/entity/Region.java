package kr.ac.hansung.kjh.seatreservationapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Region {
  DOBONG("도봉구"), GANGBUK("강북구"), NOWON("노원구"), EUNPYEONG("은평구"), SEONGBUK("성북구"), JONGNO(
      "종로구"), DONGDAEMUN(
      "동대문구"), JUNGNANG("중랑구"), SEODAEMUN("서대문구"), MAPO("마포구"), JUNG("중구"), SEONGDONG(
      "성동구"), GWANGJIN(
      "광진구"), GANGSEO("강서구"), YANGCHEON("양천구"), GURO("구로"), YEONGDEUNGPO("영등포구"), GEUMCHEON(
      "금천구"), DONGJAK(
      "동작구"), GWANAK("관악구"), SEOCHO("서초구"), GANGNAM("강남구"), SONGPA("송파구"), GANGDONG("강동구"), YONGSAN(
      "용산구");

  private final String koreanName;
}
