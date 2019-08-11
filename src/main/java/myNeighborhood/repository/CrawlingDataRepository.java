package myNeighborhood.repository;

import java.time.LocalDate;
import java.util.List;
import myNeighborhood.model.entity.CrawlingData;

/**
 * @author doyoung hwang on 2019-08-10
 */

// TODO 2-5 JpaRepository 만들기
public interface CrawlingDataRepository { // extends AAA<BBB, CCC> {


  // TODO 2-12 측정일, 동네 no로 데이터 가져오기
  List<CrawlingData> findCrawlingDataByAAA(LocalDate measurementDate, long neighborhoodNo);
}
