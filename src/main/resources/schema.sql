CREATE TABLE neighborhood
(
  neighborhood_no BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
  name            VARCHAR(100)                      NOT NULL,
  view_count      LONG                              NOT NULL default 0
);

CREATE TABLE crawling_data
(
  crawling_data_no BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
  neighborhood_no  BIGINT                            NOT NULL,
  crawling_type    VARCHAR(20)                       NOT NULL,
  measurement_date date                              NOT NULL,
  data             VARCHAR(50)                       NOT NULL,
);
