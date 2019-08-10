package myNeighborhood.service;

import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import myNeighborhood.model.entity.CrawlingData;
import myNeighborhood.model.entity.Neighborhood;
import myNeighborhood.model.enums.CrawlingType;
import myNeighborhood.repository.CrawlingDataDao;
import myNeighborhood.repository.NeighborhoodDao;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author doyoung hwang on 2019-08-04
 */

@Service
@RequiredArgsConstructor
public class BusinessService {

  private final NaverCrawlingService naverCrawlingService;
  private final NeighborhoodDao neighborhoodDao;
  private final CrawlingDataDao crawlingDataDao;

  public String getNeighborhood(String neighborhoodName) {
    List<CrawlingData> crawlingData = updateNeighborhoodAndData(neighborhoodName);

    // TODO 1-7 increaseNeighborhoodViewCount 호출

    return "내일 " + neighborhoodName + "의 온도는 " + getCrawlingData(crawlingData,
        CrawlingType.TEMPERATURE)
        + ", 미세먼지 지수는 " + getCrawlingData(crawlingData, CrawlingType.FINE_DUST) + " 입니다";
  }

  public String getNeighborhood(String neighborhoodName, CrawlingType types) {
    List<CrawlingData> crawlingData = updateNeighborhoodAndData(neighborhoodName);

    // TODO 1-8 increaseNeighborhoodViewCount 호출

    return "내일 " + neighborhoodName + "의 " + types.getKeyword() + "은 " + getCrawlingData(
        crawlingData, types) + " 입니다.";
  }

  @Transactional
  public List<CrawlingData> updateNeighborhoodAndData(String neighborhoodName) {
    Neighborhood neighborhood = neighborhoodDao.selectNeighborhood(neighborhoodName);
    if (neighborhood == null) {
      neighborhood = new Neighborhood();
      neighborhood.setName(neighborhoodName);
      neighborhoodDao.insertNeighborhood(neighborhood);
    }

    List<CrawlingData> crawlingData = crawlingDataDao
        .selectCrawlingData(LocalDate.now(), neighborhood.getNeighborhoodNo());
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
        crawlingDataDao.insertCrawlingData(cd);
        crawlingData.add(cd);
      }
    }

    return crawlingData;
  }

  private String getCrawlingData(List<CrawlingData> crawlingData, CrawlingType type) {
    return crawlingData.stream()
        .filter(d -> d.getCrawlingType() == type).findFirst().get().getData();
  }

  // TODO 1-6 호출 시 viewCount를 하나 더 한다
  private void increaseNeighborhoodViewCount(String neighborhoodName) {
    // long viewCount = neighborhoodDao.selectQQQQQ(neighborhoodName);
    // neighborhoodDao.updateHHHHH(neighborhoodName, viewCount + lll);
  }
}
