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
import DaiHoc.Molla.entity.Cart;
import DaiHoc.Molla.entity.User;
import DaiHoc.Molla.service.ICartService;
import DaiHoc.Molla.service.ICategoryService;
import DaiHoc.Molla.service.IUserService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class CartController {
	@Autowired
	private ICartService cartService;
	@Autowired
	private ICategoryService cateService;
	@Autowired
	private IUserService userService;

	@GetMapping("cart")
	public String getCart(HttpServletRequest request, ModelMap model) {
		// Handle cart
		if (CookieManager.getCookieValue(request, "user_id") != null) {
			Long user_id = Long.parseLong(CookieManager.getCookieValue(request, "user_id"));
			User user = userService.findOne(user_id);
			model.addAttribute("account", user.getAccount());
			model.addAttribute("categories", cateService.findAll());
			List<Cart> carts = cartService.findByUser(user_id);
			model.addAttribute("carts", carts);
			return "web/views/cart";
		}
		else {
			return "web/views/login";
		}
	}

	@PostMapping("add-to-cart")
	public ResponseEntity<String> postAddCart(HttpServletRequest request, @RequestParam("product_id") Long pro_id,
			@RequestParam("quantity") int quantity) {
		Long user_id = Long.parseLong(CookieManager.getCookieValue(request, "user_id"));

		if (cartService.isCartPresent(user_id, pro_id)) {
			String responseScript = "setTimeout(function() {\r\n" + "		Swal.fire({\r\n"
					+ "			icon : 'info',\r\n" + "			title : 'Sản phẩm đã có trong giỏ hàng!',\r\n"
					+ "			showConfirmButton : false,\r\n" + "			timer : 1500\r\n" + "		});\r\n"
					+ "	}, 500); ";
			return ResponseEntity.ok(responseScript);
		} else {
			cartService.create(user_id, pro_id, quantity);
			String responseScript = "setTimeout(function() {\r\n" + "		Swal.fire({\r\n"
					+ "			icon : 'success',\r\n" + "			title : 'Thêm thành công!',\r\n"
					+ "			showConfirmButton : false,\r\n" + "			timer : 1500\r\n" + "		});\r\n"
					+ "	}, 500); ";
			return ResponseEntity.ok(responseScript);
		}
	}

	@DeleteMapping("remove-cart")
	public ResponseEntity<String> deleteCart(HttpServletRequest request, @RequestParam("cart_id") Long cart_id) {
		if (cartService.deleteWithLineItem(cart_id)) {
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

	@PostMapping("updateQuantity")
	public ResponseEntity<Float> postChangeQuantity(@RequestParam("cart_id") Long cart_id,
			@RequestParam("quantity") int quantity) {
		if (cartService.changeQuantity(cart_id, quantity)) {
			return ResponseEntity.ok(cartService.findOne(cart_id).getLineItem().getProduct().getSelling_price()*quantity);
		} else {
			return ResponseEntity.ok(0f);
		}
	}

	@PostMapping("select-product")
	public ResponseEntity<Float> postSelectProduct(@RequestParam("carts_id") String carts_id) {
		try {
			String[] str_cartsId = carts_id.split(",");
			Float subtotal = 0f;
			for (String cart_id : str_cartsId) {
				Cart cart = cartService.findOne(Long.parseLong(cart_id));
				subtotal += cart.getLineItem().getSubtotal();
			}
			return ResponseEntity.ok(subtotal);
		} catch (Exception e) {
			return ResponseEntity.ok(0f);
		}
	}
}
