package myNeighborhood.repository;

import myNeighborhood.model.entity.Neighborhood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author doyoung hwang on 2019-08-10
 */

public interface NeighborhoodRepository extends JpaRepository<Neighborhood, Long> {

  // TODO 1-3 fetch join
  //  @Query("select n from Neighborhood n join fetch n.crawlingDataList where n.name = :name")
  Neighborhood findByName(@Param("name") String name);

  ViewCount findFirstByName(String name);

  interface ViewCount {

    long getViewCount();
  }
}
