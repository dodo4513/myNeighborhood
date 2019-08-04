package myNeighborhood.model.entity;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import myNeighborhood.model.enums.CrawlingType;
import org.apache.ibatis.type.Alias;

/**
 * @author doyoung hwang on 2019-08-04
 */

@Getter
@Setter
@Alias("CrawlingData")
public class CrawlingData {

  private long crawlingDataNo;

  private long neighborhoodNo;

  private CrawlingType crawlingType;

  private LocalDate measurementDate;

  private String data;
}
