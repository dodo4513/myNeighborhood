package myNeighborhood.service;

import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import myNeighborhood.model.entity.CrawlingData;
import myNeighborhood.model.entity.Neighborhood;
import myNeighborhood.model.enums.CrawlingType;
import myNeighborhood.repository.CrawlingDataRepository;
import myNeighborhood.repository.NeighborhoodDao;
import org.apache.logging.log4j.util.Strings;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author doyoung hwang on 2019-08-04
 */

@Service
@RequiredArgsConstructor
@Primary // TODO 2-10
public class BusinessServiceForJpaImpl implements BusinessService { // TODO 2-9 BusinessService 상속

  private final NaverCrawlingService naverCrawlingService;
  private final NeighborhoodDao neighborhoodDao;
  private final CrawlingDataRepository crawlingDataRepository;

  public String getNeighborhood(String neighborhoodName) {
    List<CrawlingData> crawlingData = updateNeighborhoodAndData(neighborhoodName);

    increaseNeighborhoodViewCount(neighborhoodName);

    return "내일 " + neighborhoodName + "의 온도는 " + getCrawlingData(crawlingData,
        CrawlingType.TEMPERATURE)
        + ", 미세먼지 지수는 " + getCrawlingData(crawlingData, CrawlingType.FINE_DUST) + " 입니다";
  }

  public String getNeighborhood(String neighborhoodName, CrawlingType types) {
    List<CrawlingData> crawlingData = updateNeighborhoodAndData(neighborhoodName);

    increaseNeighborhoodViewCount(neighborhoodName);

    return "내일 " + neighborhoodName + "의 " + types.getKeyword() + "은 " + getCrawlingData(
        crawlingData, types) + " 입니다.";
  }

  @Transactional(isolation = Isolation.DEFAULT)
  public List<CrawlingData> updateNeighborhoodAndData(String neighborhoodName) {
    Neighborhood neighborhood = neighborhoodDao.selectNeighborhood(neighborhoodName);
    if (neighborhood == null) {
      neighborhood = new Neighborhood();
      neighborhood.setName(neighborhoodName);
      neighborhoodDao.insertNeighborhood(neighborhood);
    }

    // TODO 2-11 jpa 교체 대상
    List<CrawlingData> crawlingData = crawlingDataRepository
        .findCrawlingDataByMeasurementDateAndNeighborhoodNo(
            LocalDate.now(), neighborhood.getNeighborhoodNo());
    if (crawlingData.size() == 0) {
      long neighborhoodNo = neighborhood.getNeighborhoodNo();
      for (CrawlingType type : CrawlingType.values()) {
        CrawlingData cd = new CrawlingData();
        cd.setNeighborhoodNo(neighborhoodNo);
        cd.setMeasurementDate(LocalDate.now());
        cd.setCrawlingType(type);
        cd.setData(naverCrawlingService.crawling(neighborhoodName, type));
        if (cd.getData().equals(Strings.EMPTY)) {
          throw new IllegalArgumentException();
        }

        // TODO 2-13 jpa 교체 대상
        crawlingDataRepository.save(cd);
        crawlingData.add(cd);
      }
    }

    return crawlingData;
  }

  private String getCrawlingData(List<CrawlingData> crawlingData, CrawlingType type) {
    return crawlingData.stream()
        .filter(d -> d.getCrawlingType() == type).findFirst().get().getData();
  }

  private void increaseNeighborhoodViewCount(String neighborhoodName) {

    long viewCount = neighborhoodDao.selectViewCount(neighborhoodName);
    neighborhoodDao.updateViewCount(neighborhoodName, viewCount + 1);
  }

  public long getNeighborhoodViewCount(String neighborhoodName) {

    return neighborhoodDao.selectViewCount(neighborhoodName);
  }
}
