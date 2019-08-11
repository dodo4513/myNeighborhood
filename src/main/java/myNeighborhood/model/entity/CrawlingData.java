package myNeighborhood.model.entity;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import myNeighborhood.model.enums.CrawlingType;
import org.apache.ibatis.type.Alias;

/**
 * @author doyoung hwang on 2019-08-04
 */

@Getter
@Setter
@Entity // TODO 2-2 JPA의 엔티티 객체를 의미
@Alias("CrawlingData")
public class CrawlingData {

  // TODO 2-3 #1 키 타입이 누구인지 지정해주고 #2 키 생성 규칙을 선언
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long crawlingDataNo;

  private long neighborhoodNo;

  private CrawlingType crawlingType;

  private LocalDate measurementDate;

  private String data;
}
