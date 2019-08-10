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

  // TODO 1-4 neighborhood view count update 메소드 선언
  // void updateHHHHH(@Param("xxx") String xxx, @Param("yyy") long yyy)

  // TODO 1-5 neighborhood view count select 메소드 언선
  // long selectQQQQQ(String xxx);
}
