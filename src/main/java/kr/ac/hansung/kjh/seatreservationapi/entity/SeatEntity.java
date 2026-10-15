package kr.ac.hansung.kjh.seatreservationapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "seats", uniqueConstraints = @UniqueConstraint(name = "uk_seat", columnNames = {
    "facility_id", "row_label", "column_no"}))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SeatEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Column(name = "facility_id", nullable = false)
  private Long facilityId;

  @NotBlank
  @Column(name = "row_label", nullable = false)
  private String rowLabel;

  @Positive
  @Column(name = "column_no", nullable = false)
  private int columnNo;

  @Builder
  private SeatEntity(Long facilityId, String rowLabel, int columnNo) {
    this.facilityId = facilityId;
    this.rowLabel = rowLabel;
    this.columnNo = columnNo;
  }

  public static SeatEntity create(Long facilityId, String rowLabel, int columnNo) {
    return SeatEntity.builder().facilityId(facilityId).rowLabel(rowLabel).columnNo(columnNo)
        .build();
  }
}
