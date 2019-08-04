package myNeighborhood.repository;

import java.time.LocalDate;
import java.util.List;
import myNeighborhood.model.entity.CrawlingData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author doyoung hwang on 2019-08-04
 */

@Mapper
public interface CrawlingDataDao {

  void insertCrawlingData(@Param("crawlingData") CrawlingData crawlingData);

  List<CrawlingData> selectCrawlingData(@Param("measurementDate") LocalDate measurementDate, @Param("neighborhoodNo") long neighborhoodNo);

}
