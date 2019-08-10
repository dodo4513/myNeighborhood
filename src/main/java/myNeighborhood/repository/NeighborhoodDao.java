package myNeighborhood.repository;

import myNeighborhood.model.entity.Neighborhood;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author doyoung hwang on 2019-08-04
 */

@Mapper
public interface NeighborhoodDao {

  void insertNeighborhood(@Param("neighborhood") Neighborhood neighborhood);

  Neighborhood selectNeighborhood(String name);

  // TODO 1-2 neighborhood view count 조회, 생성 메소드 생성
  void updateViewCount(@Param("name") String name, @Param("viewCount") long viewCount);

  long selectViewCount(String name);
}
