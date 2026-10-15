package kr.ac.hansung.kjh.seatreservationapi.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDateTime;
import java.util.List;
import kr.ac.hansung.kjh.seatreservationapi.entity.FacilityEntity;
import kr.ac.hansung.kjh.seatreservationapi.entity.FacilityType;
import kr.ac.hansung.kjh.seatreservationapi.entity.QFacilityEntity;
import kr.ac.hansung.kjh.seatreservationapi.entity.QTimeSlotEntity;
import kr.ac.hansung.kjh.seatreservationapi.entity.Region;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class CustomFacilityRepositoryImpl implements CustomFacilityRepository {

  private final JPAQueryFactory queryFactory;
  private final QFacilityEntity qFacilityEntity = QFacilityEntity.facilityEntity;
  private final QTimeSlotEntity qTimeSlotEntity = QTimeSlotEntity.timeSlotEntity;

  @Override
  public Page<FacilityEntity> findByConditions(LocalDateTime dateTime, Region region,
      FacilityType type, Pageable pageable) {
    List<FacilityEntity> content = queryFactory
        .selectFrom(qFacilityEntity)
        .where(
            isSameDateTime(dateTime),
            isSameRegion(region),
            isSameType(type)
        )
        .orderBy(qFacilityEntity.id.asc())
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .fetch();

    JPAQuery<Long> count = queryFactory
        .select(qFacilityEntity.count())
        .from(qFacilityEntity)
        .where(
            isSameDateTime(dateTime),
            isSameRegion(region),
            isSameType(type)
        );

    return PageableExecutionUtils.getPage(content, pageable, count::fetchOne);
  }

  private BooleanExpression isSameDateTime(LocalDateTime dateTime) {
    if (dateTime == null) {
      return null;
    }

    return JPAExpressions.selectOne().from(qTimeSlotEntity).where(
        qTimeSlotEntity.facilityId.eq(qFacilityEntity.id),
        qTimeSlotEntity.startTime.eq(dateTime)).exists();
  }

  private BooleanExpression isSameRegion(Region region) {
    if (region == null) {
      return null;
    }
    return qFacilityEntity.region.eq(region);
  }

  private BooleanExpression isSameType(FacilityType type) {
    if (type == null) {
      return null;
    }
    return qFacilityEntity.type.eq(type);
  }
}
