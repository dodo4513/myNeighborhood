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
@Entity // TODO 1-1 JPA의 Entity
@Alias("Neighborhood")
public class Neighborhood {

  @Id // TODO 1-2 Key 설정
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long neighborhoodNo;

  private String name;

  // TODO 1-3 view count 추가
  private long viewCount;

  public void increaseViewCount() {
    viewCount++;
  }
}
