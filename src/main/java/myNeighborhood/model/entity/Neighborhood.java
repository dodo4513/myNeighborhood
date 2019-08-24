package myNeighborhood.model.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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

  // TODO 1-2 FetchType: Lazy vs Eager
  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "neighborhoodNo")
  List<CrawlingData> crawlingDataList = new ArrayList<>();

  public void increaseViewCount() {
    viewCount++;
  }
}
