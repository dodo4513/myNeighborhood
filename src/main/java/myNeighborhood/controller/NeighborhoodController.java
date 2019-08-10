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

  private final BusinessService myService;

  @GetMapping("neighborhoods/{neighborhood}")
  public ResponseEntity<String> getNeighborhood(@PathVariable String neighborhood) {

    return ResponseEntity.ok(myService.getNeighborhood(neighborhood));
  }

  @GetMapping("neighborhoods/{neighborhood}/request-types/{types}")
  public ResponseEntity<String> getNeighborhood(@PathVariable String neighborhood,
      @PathVariable CrawlingType types) {

    return ResponseEntity.ok(myService.getNeighborhood(neighborhood, types));
  }

  @PostMapping("neighborhoods/{neighborhood}/update")
  public ResponseEntity<Boolean> updateNeighborhood(@PathVariable String neighborhood) {

    myService.updateNeighborhoodAndData(neighborhood);

    return ResponseEntity.ok(true);
  }

  // TODO 1-9 API endpoint 개발
//  @GetMapping("ABCDEFGABCDEFG")
//  public ResponseEntity<Long> getNeighborhoodViewCount(@PPPPPPPP String neighborhood) {
//
//    return ResponseEntity.ok(myService.getNeighborhoodViewCount(neighborhood));
//  }
}
