package myNeighborhood.service;

import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import myNeighborhood.model.entity.CrawlingData;
import myNeighborhood.model.entity.Neighborhood;
import myNeighborhood.model.enums.CrawlingType;
import myNeighborhood.repository.NeighborhoodRepository;
import myNeighborhood.repository.NeighborhoodRepository.ViewCount;
import org.apache.logging.log4j.util.Strings;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author doyoung hwang on 2019-08-04
 */

@Service
@RequiredArgsConstructor
@Primary
public class BusinessServiceForJpaImpl implements BusinessService {

  private final NaverCrawlingService naverCrawlingService;
  private final NeighborhoodRepository neighborhoodRepository;

  @Transactional
  public String getNeighborhood(String neighborhoodName) {
    // TODO 1-1 이 곳에 debug point!
    Neighborhood neighborhood = updateNeighborhoodAndData(neighborhoodName);

    increaseNeighborhoodViewCount(neighborhoodName);

    return "내일 " + neighborhoodName + "의 온도는 " + getCrawlingData(neighborhood.getCrawlingDataList(),
        CrawlingType.TEMPERATURE)
        + ", 미세먼지 지수는 " + getCrawlingData(neighborhood.getCrawlingDataList(),
        CrawlingType.FINE_DUST) + " 입니다. 총 " + neighborhood.getViewCount() + "번 조회됐어요.";
  }

  @Transactional
  public String getNeighborhood(String neighborhoodName, CrawlingType types) {
    Neighborhood neighborhood = updateNeighborhoodAndData(neighborhoodName);

    increaseNeighborhoodViewCount(neighborhoodName);

    return "내일 " + neighborhoodName + "의 " + types.getKeyword() + "은 " + getCrawlingData(
        neighborhood.getCrawlingDataList(), types) + " 입니다. 총 " + neighborhood.getViewCount()
        + "번 조회됐어요.";
  }

  @Transactional
  public Neighborhood updateNeighborhoodAndData(String neighborhoodName) {

    Neighborhood neighborhood = neighborhoodRepository.findByName(neighborhoodName);
    if (neighborhood == null) {
      neighborhood = new Neighborhood();
      neighborhood.setName(neighborhoodName);

      for (CrawlingType type : CrawlingType.values()) {
        CrawlingData cd = new CrawlingData();
        cd.setNeighborhoodNo(neighborhood.getNeighborhoodNo());
        cd.setMeasurementDate(LocalDate.now());
        cd.setCrawlingType(type);
        cd.setData(naverCrawlingService.crawling(neighborhoodName, type));

        if (cd.getData().equals(Strings.EMPTY)) {
          throw new IllegalArgumentException();
        }

        neighborhood.getCrawlingDataList().add(cd);
      }
    }

    neighborhoodRepository.save(neighborhood);

    return neighborhood;
  }

  private String getCrawlingData(List<CrawlingData> crawlingData, CrawlingType type) {
    return crawlingData.stream()
        .filter(d -> d.getCrawlingType() == type).findFirst().get().getData();
  }

  private void increaseNeighborhoodViewCount(String neighborhoodName) {
    Neighborhood neighborhood = neighborhoodRepository.findByName(neighborhoodName);

    neighborhood.increaseViewCount();
  }

  public long getNeighborhoodViewCount(String neighborhoodName) {
    ViewCount viewCount = neighborhoodRepository.findFirstByName(neighborhoodName);

    if (viewCount != null) {
      return viewCount.getViewCount();
    }

    return 0;
  }
}
