package myNeighborhood.model.enums;

import lombok.Getter;

/**
 * @author doyoung hwang on 2019-08-04
 */

@Getter
public enum CrawlingType {
  // TODO 1-1 우리의 목표는 아래처럼 미리 정의된 phrase 에 맞춰  *repository.*save() 가 호출될 때 변환하고 저장하게 하자.
  TEMPERATURE("날씨",
      "온도는 {data}도",
      " #main_pack > div.sc.cs_weather._weather > div:nth-child(2) > div.weather_box > div.weather_area._mainArea > div.table_info.weekly._weeklyWeather > ul:nth-child(2) > li:nth-child(2) > dl > dd > span:nth-child(1)"),
  FINE_DUST("내일미세먼지", "미세먼지 지수는 {data}",
      " #main_pack > div.content_search.section._atmospheric_environment > div > div.contents03_sub > div > div:nth-child(5) > div.main_box > div > div.tb_scroll > table > tbody > tr:nth-child(1) > td:nth-child(2) > span");

  String keyword;
  String phrase;
  String query;

  CrawlingType(String keyword, String phrase, String query) {
    this.keyword = keyword;
    this.phrase = phrase;
    this.query = query;
  }

  // TODO 1-2 data를 넣으면 phrase와 조합해 적절한 문구를 만들어낸다.
  public String createBy(String data) {
    return phrase.replace("{data}", data);
  }
}
