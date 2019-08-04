package myNeighborhood.model.enums;

import lombok.Getter;

/**
 * @author doyoung hwang on 2019-08-04
 */

@Getter
public enum CrawlingType {
  TEMPERATURE("날씨",
      " #main_pack > div.sc.cs_weather._weather > div:nth-child(2) > div.weather_box > div.weather_area._mainArea > div.table_info.weekly._weeklyWeather > ul:nth-child(2) > li:nth-child(2) > dl > dd > span:nth-child(1)"),
  FINE_DUST("내일미세먼지",
      " #main_pack > div.content_search.section._atmospheric_environment > div > div.contents03_sub > div > div:nth-child(5) > div.main_box > div > div.tb_scroll > table > tbody > tr:nth-child(1) > td:nth-child(2) > span");

  String keyword;
  String query;

  CrawlingType(String keyword, String query) {
    this.keyword = keyword;
    this.query = query;
  }
}
