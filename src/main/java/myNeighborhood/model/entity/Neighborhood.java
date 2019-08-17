package myNeighborhood.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

/**
 * @author doyoung hwang on 2019-08-04
 */

@Getter
@Setter
@Entity
@Alias("Neighborhood")
public class Neighborhood {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long neighborhoodNo;

  private String name;

  private long viewCount;

  public void increaseViewCount() {
    viewCount++;
  }
}
