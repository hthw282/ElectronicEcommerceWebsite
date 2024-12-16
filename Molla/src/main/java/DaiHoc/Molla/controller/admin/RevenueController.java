package DaiHoc.Molla.controller.admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import DaiHoc.Molla.entity.Bill;
import DaiHoc.Molla.service.Imp.BillService;

@Controller
@RequestMapping("/admin")
public class RevenueController {
	@Autowired
	private BillService billService;
	
	@GetMapping("/revenue")
	public String getRevenue(@RequestParam(defaultValue = "2024", value = "year", required = false) Integer year, ModelMap model) {
		if (year == null) {
            year = LocalDate.now().getYear(); // Default to the current year if no year is selected
        }
        
        Map<String, Double> monthlyRevenue = billService.getMonthlyRevenueByYear(year);
        List<Integer> years = billService.getAvailableYears();

        model.addAttribute("monthlyRevenue", monthlyRevenue);
        model.addAttribute("years", years);
        model.addAttribute("selectedYear", year);

		return "admin/views/revenue/totalrevenue";
	}

}
