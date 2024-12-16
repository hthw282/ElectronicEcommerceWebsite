package DaiHoc.Molla.controller.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import DaiHoc.Molla.Utils.CookieManager;
import DaiHoc.Molla.entity.Favourite;
import DaiHoc.Molla.entity.Product;
import DaiHoc.Molla.entity.User;
import DaiHoc.Molla.service.ICategoryService;
import DaiHoc.Molla.service.IFavouriteService;
import DaiHoc.Molla.service.IProductService;
import DaiHoc.Molla.service.IUserService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class FavouriteController {
	@Autowired
	private IFavouriteService service;
	@Autowired
	private ICategoryService cateService;
	@Autowired
	private IProductService proService;
	@Autowired
	private IUserService userService;

	@GetMapping("favourite")
	public String getDetail(HttpServletRequest request, ModelMap model) {
		try {
			model.addAttribute("categories", cateService.findAll());
			Long user_id = Long.parseLong(CookieManager.getCookieValue(request, "user_id"));
			User user = userService.findOne(user_id);
			model.addAttribute("account", user.getAccount());
			List<Favourite> favourites = (List<Favourite>) service.findAll(user);
			service.setState(favourites); // state: 0 - còn hàng, 1 - hết hàng
			model.addAttribute("favourites", favourites);
			return "web/views/favourite";
		} catch (Exception e) {
			return "web/views/login";
		}
	}

	@PostMapping("add-to-fav")
	public ResponseEntity<String> postAddToFavourite(HttpServletRequest request,
			@RequestParam("product_id") Long product_id, ModelMap model) {
		try {
			// Get product and user entity
			Long user_id = Long.parseLong(CookieManager.getCookieValue(request, "user_id"));
			User user = userService.findOne(user_id);
			Product product = proService.findOne(product_id);

			// Check isPresent
			if (service.findOne(product, user) == null) {
				new Favourite();
				Favourite fav = Favourite.builder().product(product).user(user).build();
				service.create(fav);
				String responseScript = "setTimeout(function() {\r\n" + "		Swal.fire({\r\n"
						+ "			icon : 'success',\r\n" + "			title : 'Thêm thành công!',\r\n"
						+ "			showConfirmButton : false,\r\n" + "			timer : 1000\r\n" + "		});\r\n"
						+ "	}, 0); ";
				return ResponseEntity.ok(responseScript);
			} else {
				String responseScript = "setTimeout(function() {\r\n" + "		Swal.fire({\r\n"
						+ "			icon : 'info',\r\n" + "			title : 'Đã thêm vào yêu thích!',\r\n"
						+ "			showConfirmButton : false,\r\n" + "			timer : 1000\r\n" + "		});\r\n"
						+ "	}, 0); ";
				return ResponseEntity.ok(responseScript);
			}

		} catch (Exception e) {
			e.printStackTrace();
			String responseScript = "setTimeout(function() {\r\n" + "		Swal.fire({\r\n"
					+ "			icon : 'error',\r\n" + "			title : 'Thất bại!',\r\n"
					+ "			showConfirmButton : false,\r\n" + "			timer : 1000\r\n" + "		});\r\n"
					+ "	}, 0); ";
			return ResponseEntity.ok(responseScript);
		}
	}

	@DeleteMapping("remove-fav")
	public ResponseEntity<String> deleteCart(HttpServletRequest request, @RequestParam("fav_id") Long fav_id) {
		if (service.delete(fav_id)) {
			String responseScript = "setTimeout(function() {\r\n" + "		Swal.fire({\r\n"
					+ "			icon : 'success',\r\n" + "			title : 'Xóa thành công!',\r\n"
					+ "			showConfirmButton : false,\r\n" + "			timer : 1000\r\n" + "		});\r\n"
					+ "	}, 0); ";
			return ResponseEntity.ok(responseScript);
		} else {
			String responseScript = "setTimeout(function() {\r\n" + "		Swal.fire({\r\n"
					+ "			icon : 'error',\r\n" + "			title : 'Thất bại!',\r\n"
					+ "			showConfirmButton : false,\r\n" + "			timer : 1000\r\n" + "		});\r\n"
					+ "	}, 0); ";
			return ResponseEntity.ok(responseScript);
		}
	}
}
