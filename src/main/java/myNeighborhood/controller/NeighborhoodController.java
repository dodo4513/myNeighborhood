package myNeighborhood.controller;

import lombok.RequiredArgsConstructor;
import myNeighborhood.model.enums.CrawlingType;
import myNeighborhood.service.BusinessService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author doyoung hwang on 2019-08-04
 */

@RestController
@RequiredArgsConstructor
public class NeighborhoodController {

  private final BusinessService businessService; // TODO 2-6 interface 로 변경

  @GetMapping("neighborhoods/{neighborhood}")
  public ResponseEntity<String> getNeighborhood(@PathVariable String neighborhood) {

    return ResponseEntity.ok(businessService.getNeighborhood(neighborhood));
  }

  @GetMapping("neighborhoods/{neighborhood}/request-types/{types}")
  public ResponseEntity<String> getNeighborhood(@PathVariable String neighborhood,
      @PathVariable CrawlingType types) {

    return ResponseEntity.ok(businessService.getNeighborhood(neighborhood, types));
  }

  @PostMapping("neighborhoods/{neighborhood}/update")
  public ResponseEntity<Boolean> updateNeighborhood(@PathVariable String neighborhood) {

    businessService.updateNeighborhoodAndData(neighborhood);

    return ResponseEntity.ok(true);
  }

  @GetMapping("neighborhoods/{neighborhood}/view-count")
  public ResponseEntity<Long> getNeighborhoodViewCount(@PathVariable String neighborhood) {

    return ResponseEntity.ok(businessService.getNeighborhoodViewCount(neighborhood));
  }
}
