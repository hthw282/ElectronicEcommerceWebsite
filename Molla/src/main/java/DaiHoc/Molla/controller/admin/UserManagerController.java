package DaiHoc.Molla.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import DaiHoc.Molla.entity.Bill;
import DaiHoc.Molla.entity.Transaction;
import DaiHoc.Molla.entity.User;
import DaiHoc.Molla.repository.TransactionRepository;
import DaiHoc.Molla.service.Imp.BillService;
import DaiHoc.Molla.service.Imp.UserService;

@Controller
@RequestMapping("/admin")
public class UserManagerController {
	@Autowired
	private UserService userService;
	@Autowired
	private BillService billService;
	@Autowired
	private TransactionRepository transRepo;
	@GetMapping(value = { "/user", "" })
	public String UserMangerPage(Model model,@Param("keyword")String keyword
			,@RequestParam(name="pageNo", defaultValue="1") Integer pageNo) {
		Page<User> list = userService.getAll(pageNo);
		if(keyword != null) {
			list = userService.searchUser(keyword,pageNo);
			model.addAttribute("keyword", keyword);
		}
		model.addAttribute("totalPage", list.getTotalPages());
		model.addAttribute("curPage", pageNo);
		model.addAttribute("list", list);

		return "/admin/views/user/UserManager";
	}
	@GetMapping("/show-info-user/{id}")
	public String showInfoUser(Model model, @PathVariable("id") Long id) {
		User user = userService.findOne(id);
		model.addAttribute("user", user);
		return "admin/views/user/UserInfo";		
	}
	
	@GetMapping("/update-user/{id}")
	public String editUser(Model model, @PathVariable("id") Long id) {
		User user = userService.findOne(id);
		model.addAttribute("user", user);
		return "admin/views/user/UpdateUser";		
	}
	
	@PostMapping("/update-user")
	public String updateUser(@ModelAttribute("user")User user) {
		if(userService.update(user) ) {
			return "redirect:/admin/user";
		}	
		return "redirect:/admin";
	}
	
	@GetMapping("/purchase-history/{id}")
	public String PurchaseHistory(Model model, @PathVariable("id") Long id) {
		List<Bill> listbill = billService.findBillByUserId(id);
		model.addAttribute("listbill", listbill);
		return "admin/views/user/PurchaseHistory";		
	}
	@GetMapping("/bill-detail/{id}")
	public String BillDetail(Model model, @PathVariable("id") Long id) {
		Bill bill = billService.findOne(id);
		List<Transaction> listtrans = transRepo.findTransactionByBillId(id);
		System.out.println(listtrans.size());
		model.addAttribute("listtrans", listtrans);
		model.addAttribute("bill", bill);
		
		return "admin/views/user/BillDetail";		
	}
}
	