package myNeighborhood.service;

import java.io.IOException;
import myNeighborhood.model.enums.CrawlingType;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

/**
 * @author doyoung hwang on 2019-08-04
 */

@Service
public class NaverCrawlingService {

  private static final String NAVER_SEARCH_URL = "https://search.naver.com/search.naver?sm=top_hty&fbm=1&ie=utf8&query=";

  String crawling(String neighborhood, CrawlingType type) {
    System.out.println("Call naver!");
    try {
      String URL = NAVER_SEARCH_URL + neighborhood + type.getKeyword();
      Document doc = Jsoup.connect(URL).get();

      Elements elem = doc.select(type.getQuery());
      return elem.text();
    } catch (IOException ignored) {
    }
    throw new IllegalArgumentException();
  }
}
