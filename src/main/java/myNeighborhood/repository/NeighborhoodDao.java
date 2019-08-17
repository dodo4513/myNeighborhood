package myNeighborhood.repository;

import myNeighborhood.model.entity.Neighborhood;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author doyoung hwang on 2019-08-04
 */

@Deprecated
@Mapper
public interface NeighborhoodDao {

  void insertNeighborhood(@Param("neighborhood") Neighborhood neighborhood);

  Neighborhood selectNeighborhood(String name);

  void updateViewCount(@Param("name") String name, @Param("viewCount") long viewCount);

  long selectViewCount(String name);
}
