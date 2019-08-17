package myNeighborhood.repository;

import myNeighborhood.model.entity.Neighborhood;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author doyoung hwang on 2019-08-10
 */

public interface NeighborhoodRepository extends JpaRepository<Neighborhood, Long> {

  Neighborhood findByName(String name);

  ViewCount findFirstByName(String name);

  interface ViewCount {

    long getViewCount();
  }
}
