package myNeighborhood.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

/**
 * @author doyoung hwang on 2019-08-04
 */

@Getter
@Setter
@Alias("Neighborhood")
public class Neighborhood {

  private long neighborhoodNo;

  private String name;
}
