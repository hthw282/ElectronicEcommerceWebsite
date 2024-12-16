package DaiHoc.Molla.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import DaiHoc.Molla.service.IProductService;
import DaiHoc.Molla.service.IReviewService;

@RestController
@RequestMapping("/api/review")
public class ReviewAPIController {
	@Autowired
	private IReviewService service;
	@GetMapping("")
	public ResponseEntity<?> getReview(){
		
		return ResponseEntity.ok(service.findByProduct_Id(1L).get());
	}
}
