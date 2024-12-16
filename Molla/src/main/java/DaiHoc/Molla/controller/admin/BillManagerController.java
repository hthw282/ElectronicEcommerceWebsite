package DaiHoc.Molla.controller.admin;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import DaiHoc.Molla.entity.Bill;
import DaiHoc.Molla.service.IBillService;

@Controller
@RequestMapping("/admin")
public class BillManagerController {
	@Autowired
	private IBillService billService;
	
	@GetMapping("/bill")
	public String getAdminBill(ModelMap model) {
		List<Bill> bills = billService.findAll();
		Collections.reverse(bills);
		model.addAttribute("bills", bills);
		return "admin/views/bill/list";
	}
	
	@GetMapping("/bill-detail")
	public String getAdminBillDetail(@RequestParam Long id, ModelMap model) {
		Bill bill = billService.findOne(id);
		model.addAttribute("bill", bill);
		return "admin/views/bill/detail";
	}
	
	@PostMapping("/update-bill")
	public String postAdminBillDetail(@RequestParam Long id, @RequestParam int state, ModelMap model) {
		Bill bill = billService.findOne(id);
		bill.setState(state);
		billService.update(bill);
		System.out.println(bill.getState());
		model.addAttribute("bill", bill);
		return "redirect:bill-detail?id="+id;
	}
}
