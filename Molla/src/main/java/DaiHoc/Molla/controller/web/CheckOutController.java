package DaiHoc.Molla.controller.web;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import DaiHoc.Molla.Utils.CookieManager;
import DaiHoc.Molla.config.Config;
import DaiHoc.Molla.entity.Bill;
import DaiHoc.Molla.entity.Cart;
import DaiHoc.Molla.entity.PromotionalCode;
import DaiHoc.Molla.entity.Transaction;
import DaiHoc.Molla.entity.User;
import DaiHoc.Molla.service.IBillService;
import DaiHoc.Molla.service.ICartService;
import DaiHoc.Molla.service.ICategoryService;
import DaiHoc.Molla.service.IPromotionalCodeService;
import DaiHoc.Molla.service.ITransactionService;
import DaiHoc.Molla.service.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/")
public class CheckOutController {
	@Autowired
	private ICartService cartService;
	@Autowired
	private ICategoryService cateService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IPromotionalCodeService codeService;
	@Autowired
	private ITransactionService transService;
	@Autowired
	private IBillService billService;

	@GetMapping("checkout")
	public String getCheckout(@RequestParam String carts_id, @RequestParam Float ship, ModelMap model,
			HttpServletRequest request) {

		Long user_id = Long.parseLong(CookieManager.getCookieValue(request, "user_id"));
		User user = userService.findOne(user_id);
		model.addAttribute("account", user.getAccount());
		String[] str_cartsId = carts_id.split(",");
		List<Cart> carts = new ArrayList<Cart>();
		Float subtotal = 0F;
		for (String cart_id : str_cartsId) {
			Cart cart = cartService.findOne(Long.parseLong(cart_id));
			carts.add(cart);
			subtotal += cart.getLineItem().getSubtotal();
		}
		model.addAttribute("carts", carts);
		model.addAttribute("subtotal", subtotal);
		model.addAttribute("ship", ship);
		model.addAttribute("total", subtotal + ship);
		return "web/views/checkout";
	}

	@PostMapping("checkout")
	public String postCheckout(@RequestParam("paymentMethod") String paymentMethod, ModelMap model,
			HttpServletRequest request, RedirectAttributes redirectAttributes) {
		try {
			model.addAttribute("categories", cateService.findAll());
			Long user_id = Long.parseLong(CookieManager.getCookieValue(request, "user_id"));
			User user = userService.findOne(user_id);
			model.addAttribute("account", user.getAccount());

			String address_ship = "";
			address_ship += (request.getParameter("more_address") != "") ? request.getParameter("more_address") + ", "
					: "";
			address_ship += request.getParameter("address") + ", " + request.getParameter("Province") + ", "
					+ request.getParameter("city") + ", " + request.getParameter("country");
			// Tạo bill
			new Bill();
			Bill bill = Bill.builder().user(userService.findOne(user_id))
					.product_price(Float.parseFloat(request.getParameter("subtotal")))
					.ship(Float.parseFloat(request.getParameter("ship")))
					.total_price(Float.parseFloat(request.getParameter("total")))
					.bill_date(Date.valueOf(LocalDate.now())).state(0).receiver(request.getParameter("name"))
					.address_shipment(address_ship).phone_shipment(request.getParameter("Phone"))
					.email(request.getParameter("email")).note(request.getParameter("note")).build();
			if (request.getParameter("code") != null) {
				bill.setPromotionalCode(
						(PromotionalCode) codeService.getOne(Long.parseLong(request.getParameter("code"))).get());
			}

			Bill savedBill = billService.create(bill);

			String[] carts_id = (String[]) request.getParameterValues("cart");
			for (String cart_id : carts_id) {
				Long id = Long.parseLong(cart_id);
				Cart cart = cartService.findOne(id);
				// Xóa cart
				cartService.delete(cart.getId());
				// Thêm transaction
				Transaction trans = new Transaction();
				trans.setLineItem(cart.getLineItem());
				trans.setBill(bill);
				trans.set_review(false);
				transService.create(Optional.ofNullable(trans));
			}
			if (paymentMethod.equals("cash")) {

				return "web/views/thank";
			} else if (paymentMethod.equals("vnpay")) {
				redirectAttributes.addFlashAttribute("bill", savedBill);
				System.out.println("Bill id: " + savedBill.getId());
				return "redirect:vnpay";
			}
			return "web/views/404";
		} catch (Exception e) {
			e.printStackTrace();
			return "web/views/404";
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("vnpay")
	public void getPay(HttpServletRequest req, HttpServletResponse resp, Model model) throws IOException {
		String vnp_Version = "2.1.0";
		String vnp_Command = "pay";
		String orderType = "other";
		Bill bill = (Bill) model.getAttribute("bill");
		long amount = (long) bill.getTotal_price() * 100;
		String bankCode = req.getParameter("bankCode");

		String vnp_TxnRef = Config.getRandomNumber(8);
		String vnp_IpAddr = Config.getIpAddress(req);

		String vnp_TmnCode = Config.vnp_TmnCode;

		Map<String, String> vnp_Params = new HashMap<>();
		vnp_Params.put("vnp_Version", vnp_Version);
		vnp_Params.put("vnp_Command", vnp_Command);
		vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
		vnp_Params.put("vnp_Amount", String.valueOf(amount));
		vnp_Params.put("vnp_CurrCode", "VND");

		if (bankCode != null && !bankCode.isEmpty()) {
			vnp_Params.put("vnp_BankCode", bankCode);
		}
		vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
		vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
		vnp_Params.put("vnp_OrderType", orderType);

		String locate = req.getParameter("language");
		if (locate != null && !locate.isEmpty()) {
			vnp_Params.put("vnp_Locale", locate);
		} else {
			vnp_Params.put("vnp_Locale", "vn");
		}
		vnp_Params.put("vnp_ReturnUrl", Config.vnp_ReturnUrl + "?bill_id=" + bill.getId());
		vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

		Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String vnp_CreateDate = formatter.format(cld.getTime());
		vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

		cld.add(Calendar.MINUTE, 15);
		String vnp_ExpireDate = formatter.format(cld.getTime());
		vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

		List fieldNames = new ArrayList(vnp_Params.keySet());
		Collections.sort(fieldNames);
		StringBuilder hashData = new StringBuilder();
		StringBuilder query = new StringBuilder();
		Iterator itr = fieldNames.iterator();
		while (itr.hasNext()) {
			String fieldName = (String) itr.next();
			String fieldValue = (String) vnp_Params.get(fieldName);
			if ((fieldValue != null) && (fieldValue.length() > 0)) {
				// Build hash data
				hashData.append(fieldName);
				hashData.append('=');
				hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
				// Build query
				query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
				query.append('=');
				query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
				if (itr.hasNext()) {
					query.append('&');
					hashData.append('&');
				}
			}
		}
		String queryUrl = query.toString();
		String vnp_SecureHash = Config.hmacSHA512(Config.secretKey, hashData.toString());
		queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
		String paymentUrl = Config.vnp_PayUrl + "?" + queryUrl;
		resp.sendRedirect(paymentUrl);
	}

	@GetMapping("/vnpay-return")
	public String vnpayReturn(@RequestParam Long bill_id, @RequestParam Map<String, String> allParams) {
		String vnp_ResponseCode = allParams.get("vnp_ResponseCode");

		// Kiểm tra mã phản hồi của VNPAY
		if ("00".equals(vnp_ResponseCode)) {
			// Thanh toán thành công
			Bill bill = billService.findOne(bill_id);
			bill.setState(1);
			billService.update(bill);
			return "web/views/thank";
		} else {
			// Thanh toán thất bại
			Bill bill = billService.findOne(bill_id);
			bill.setState(2);
			billService.update(bill);
			return "web/views/404";
		}
	}
}
