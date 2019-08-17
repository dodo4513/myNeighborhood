package myNeighborhood.service;

import myNeighborhood.model.enums.CrawlingType;

/**
 * @author doyoung hwang on 2019-08-11
 */

public interface BusinessService {

  String getNeighborhood(String neighborhoodName);

  String getNeighborhood(String neighborhoodName, CrawlingType types);

  Object updateNeighborhoodAndData(String neighborhoodName);

  long getNeighborhoodViewCount(String neighborhoodName);
}
