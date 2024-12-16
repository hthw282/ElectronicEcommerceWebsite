package DaiHoc.Molla.controller.web;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import DaiHoc.Molla.Utils.CookieManager;
import DaiHoc.Molla.Utils.ImageFileFounder;
import DaiHoc.Molla.entity.Product;
import DaiHoc.Molla.entity.User;
import DaiHoc.Molla.service.ICategoryService;
import DaiHoc.Molla.service.IManufacturerService;
import DaiHoc.Molla.service.IProductService;
import DaiHoc.Molla.service.IUserService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class HomeController {
	@Autowired
	private IProductService productService;
	@Autowired
	private IProductService service;
	@Autowired
	private ICategoryService cateService;
	@Autowired
	private IManufacturerService manuService;
	@Autowired
	private IUserService userService;
	@Autowired
	private UserDetailsService userDetailsService;

	@GetMapping(value = { "home", "index", "" })
	public String getHome(ModelMap model, Principal principal) {
		// Handle header
		model.addAttribute("urlPage", "home");
		// đoạn này để load tên lên trang homepage
		if (principal != null) {
			UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
			model.addAttribute("account", userDetails);
		}

		// Handle banner
		List<String> banners = ImageFileFounder.findImageFiles("src/main/resources/static/assets/images/banners");
		model.addAttribute("banners", banners);

		// Handle new product
		model.addAttribute("news", service.findNewProduct());

		// Handle best seller product
		model.addAttribute("best_sellers", service.findBestSellerProduct());

		// Handle on sale product
		model.addAttribute("on_sales", service.findOnSaleProduct());

		// Handle product of category
		model.addAttribute("categories", cateService.findAll());

		return "web/views/home";
	}

	@GetMapping("search")
	public String getSearch(HttpServletRequest request, @RequestParam() String q, ModelMap model) {
		try {
			model.addAttribute("urlPage", "product");

			Long user_id = Long.parseLong(CookieManager.getCookieValue(request, "user_id"));
			User user = userService.findOne(user_id);
			model.addAttribute("account", user.getAccount());
			List<Product> products = (List<Product>) productService.search(q);

			model.addAttribute("products", products);
			model.addAttribute("sortby", 0);
			model.addAttribute("page", 1);
			model.addAttribute("countPage", productService.calculatePage(products));
			model.addAttribute("categories", cateService.findAll());
			model.addAttribute("manufacturers", manuService.findAll());
			model.addAttribute("cate_id", 0);
			model.addAttribute("manu_id", 0);
			return "web/views/products";
		} catch (Exception e) {
			e.printStackTrace();
			return "web/views/404";
		}
	}
}
