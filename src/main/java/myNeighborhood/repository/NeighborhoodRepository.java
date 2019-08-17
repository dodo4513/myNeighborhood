package myNeighborhood.repository;

import myNeighborhood.model.entity.Neighborhood;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author doyoung hwang on 2019-08-10
 */

// TODO 1-4 Repository
public interface NeighborhoodRepository extends JpaRepository<Neighborhood, Long> {

//  Neighborhood findByxxxx(String name);

//  ViewCount findFirstByxxxx(String name);

//  interface ViewCount {
//
//    long getViewCount();
//  }
}
