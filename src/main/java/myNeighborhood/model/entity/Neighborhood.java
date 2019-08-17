package myNeighborhood.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

/**
 * @author doyoung hwang on 2019-08-04
 */

@Getter
@Setter
// @Exxxxx // TODO 1-1 JPA의 Entity
@Alias("Neighborhood")
public class Neighborhood {

  // @Ix // TODO 1-2 Key 설정
  // @Gxxxxxxxxxxxx(strategy = GenerationType.IDENTITY)
  private long neighborhoodNo;

  private String name;

  // TODO 1-3 view count 추가
  // private long viewCount;

//  public void increaseViewCount() {
//    viewCount++;
//  }
}
