package kr.ac.hansung.kjh.seatreservationapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "facilities")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FacilityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Region region;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "facility_type", nullable = false)
    private FacilityType type;

    @Builder
    private FacilityEntity(String name, Region region, FacilityType type) {
        this.name = name;
        this.region = region;
        this.type = type;
    }

    public static FacilityEntity create(String name, Region region, FacilityType type) {
        return FacilityEntity.builder()
                .name(name)
                .region(region)
                .type(type)
                .build();
    }
}
