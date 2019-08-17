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
@Entity
@Alias("CrawlingData")
public class CrawlingData {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long crawlingDataNo;

  private long neighborhoodNo;

  private CrawlingType crawlingType;

  private LocalDate measurementDate;

  private String data;
}
