package myNeighborhood.repository;

import java.time.LocalDate;
import java.util.List;
import myNeighborhood.model.entity.CrawlingData;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author doyoung hwang on 2019-08-10
 */

@Deprecated
public interface CrawlingDataRepository extends JpaRepository<CrawlingData, Long> {

  List<CrawlingData> findCrawlingDataByMeasurementDateAndNeighborhoodNo(LocalDate measurementDate,
      long neighborhoodNo);
}
