package DaiHoc.Molla.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import DaiHoc.Molla.repository.AccountRepository;
import DaiHoc.Molla.repository.ProductRepository;
import DaiHoc.Molla.repository.UserRepository;
import DaiHoc.Molla.service.ICartService;
import DaiHoc.Molla.service.IProductService;

@RestController
@RequestMapping("/api/product")
public class ProductAPIController {
	@Autowired
	private IProductService service;
	@Autowired
	private ICartService service1;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private ProductRepository proRepo;
	@Autowired
	private AccountRepository accRepo;
	@DeleteMapping("")
	public ResponseEntity<?> getProduct(){
		return ResponseEntity.ok(service1.delete(4L));
	}
}
