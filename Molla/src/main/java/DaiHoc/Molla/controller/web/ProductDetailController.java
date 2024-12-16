package DaiHoc.Molla.controller.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import DaiHoc.Molla.Utils.CookieManager;
import DaiHoc.Molla.entity.Product;
import DaiHoc.Molla.entity.Review;
import DaiHoc.Molla.entity.User;
import DaiHoc.Molla.service.IProductService;
import DaiHoc.Molla.service.IReviewService;
import DaiHoc.Molla.service.IUserService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class ProductDetailController {
	@Autowired
	private IProductService productService;
	@Autowired
	private IReviewService reviewService;
	@Autowired
	private IUserService userService;

	@SuppressWarnings("unchecked")
	@GetMapping("detail")
	public String getDetail(HttpServletRequest request, @RequestParam Long id, ModelMap model) {
		// Handle header
		model.addAttribute("urlPage", "product");

		if (CookieManager.getCookieValue(request, "user_id") != null) {
			Long user_id = Long.parseLong(CookieManager.getCookieValue(request, "user_id"));
			User user = userService.findOne(user_id);
			model.addAttribute("account", user.getAccount());
		}
		
		List<Review> reviews = (List<Review>) reviewService.findByProduct_Id(id).get();
		Product product = (Product) productService.findOne(id);
		model.addAttribute("product", product);
		model.addAttribute("top4_product", productService.findTop4Product());
		model.addAttribute("reviews", reviews);
		model.addAttribute("countReview", reviews.size());
		model.addAttribute("categories", productService.findByCategory(product.getCategory().getId()));
		model.addAttribute("manufacturers", productService.findByManufacturer(product.getManufacturer().getId()));
		return "web/views/detail";

	}
}
