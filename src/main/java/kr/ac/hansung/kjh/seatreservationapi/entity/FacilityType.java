package kr.ac.hansung.kjh.seatreservationapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FacilityType {
  THEATER("영화관"),
  STUDY_CAFE("스터디카페"),
  HOSPITAL("병원");

  private final String koreanName;
}
