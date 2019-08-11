package myNeighborhood.service;

import java.util.List;
import myNeighborhood.model.entity.CrawlingData;
import myNeighborhood.model.enums.CrawlingType;

/**
 * @author doyoung hwang on 2019-08-11
 */

// TODO 2-6 Mybatis용 JPA용 BusinessService 를 만들기 위해선 인터페이스가 필요
public interface BusinessService {

  String getNeighborhood(String neighborhoodName);

  String getNeighborhood(String neighborhoodName, CrawlingType types);

  List<CrawlingData> updateNeighborhoodAndData(String neighborhoodName);

  long getNeighborhoodViewCount(String neighborhoodName);
}
