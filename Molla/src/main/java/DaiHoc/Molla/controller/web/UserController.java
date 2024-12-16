package DaiHoc.Molla.controller.web;

import java.security.Principal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import DaiHoc.Molla.Utils.CookieManager;
import DaiHoc.Molla.entity.Account;
import DaiHoc.Molla.entity.Bill;
import DaiHoc.Molla.entity.Review;
import DaiHoc.Molla.entity.Transaction;
import DaiHoc.Molla.entity.User;
import DaiHoc.Molla.service.IAccountService;
import DaiHoc.Molla.service.IBillService;
import DaiHoc.Molla.service.IProductService;
import DaiHoc.Molla.service.IReviewService;
import DaiHoc.Molla.service.ITransactionService;
import DaiHoc.Molla.service.IUserService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("")
public class UserController {

	@Autowired
	private IUserService userService;
	@Autowired
	private IBillService billService;
	@Autowired
	private IAccountService accService;
	@Autowired
	private ITransactionService transService;
	@Autowired
	private IReviewService rvService;
	@Autowired
	private IProductService proService;
	@Autowired
	UserDetailsService userDetailsService;

	@GetMapping("/customer")
	public String InformationUserPage(Model model, Principal principal) {
		if (principal != null) {
			UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
			model.addAttribute("account", userDetails);
			Account acc = accService.findOne(userDetails.getUsername());
			if (acc.getUser() != null) {
				User u = userService.findOne(acc.getUser().getId());
				model.addAttribute("user", u);

				if (u.getBills() != null) {
					List<Bill> list = billService.findBillByUserId(u.getId());
					Collections.reverse(list);
					model.addAttribute("list", list);
				} else {
					model.addAttribute("list", null);
				}
			}

			else {
				model.addAttribute("user", null);
			}
		}

		return "/web/views/customer/Infor";
	}

	@PostMapping("/update-infor")
	public String updateInfor(@ModelAttribute("user") User user) {
		if (userService.update(user)) {
			return "redirect:/web/customer";
		}

		return "redirect:/update-infor";
	}

	@GetMapping("/bill-detail")
	public String billDetail(@RequestParam Long id, Model model, Principal principal) {
		// đoạn này để load tên lên trang homepage
		if (principal != null) {
			UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
			model.addAttribute("account", userDetails);
		}
		Bill bill = billService.findOne(id);

		List<Transaction> listtrans = transService.findTransactionByBillId(id);
		model.addAttribute("listtrans", listtrans);
		model.addAttribute("bill", bill);

		return "/web/views/customer/billDetailCus";
	}

	@PostMapping("/review")
	public String postReview(HttpServletRequest request, @RequestParam Long bill_id, @RequestParam Long trans_id,
			@RequestParam String title, @RequestParam String content, @RequestParam int rating, Model model,
			Principal principal) {
		// đoạn này để load tên lên trang homepage
		if (principal != null) {
			UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
			model.addAttribute("account", userDetails);
		}
		Long user_id = Long.parseLong(CookieManager.getCookieValue(request, "user_id"));
		User user = userService.findOne(user_id);
		Transaction tran = transService.findOne(trans_id);
		tran.set_review(true);

		new Review();
		Review rv = Review.builder().title(title).content(content).rating(rating)
				.review_date(Date.valueOf(LocalDate.now())).user(user).product(tran.getLineItem().getProduct()).build();
		rvService.create(rv);
		// Update lại rating của product
		proService.updateRating(tran.getLineItem().getProduct());
		return "redirect:bill-detail?id=" + bill_id;
	}
}
