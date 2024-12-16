package DaiHoc.Molla.controller.admin;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import DaiHoc.Molla.service.Imp.BillService;

@Controller
@RequestMapping("/admin")
public class StatisticsController {
	@Autowired
	private BillService billService;

	@GetMapping("/statistic")
	public String getRevenue(@RequestParam(defaultValue = "0") int year,
			@RequestParam(defaultValue = "0") int month, ModelMap model) {
		if (year == 0) {
			year = LocalDate.now().getYear();
		}
		if (month == 0) {
			month = LocalDate.now().getMonthValue();
		}

		Map<String, Integer> productStatistics = billService.getSoldProductsStatistics(month, year);
		model.addAttribute("productStatistics", productStatistics);
		
        List<Integer> years = billService.getAvailableYears();
        model.addAttribute("years", years);

		model.addAttribute("selectedYear", year);
		model.addAttribute("selectedMonth", month);
		return "admin/views/statistics/list";
	}
}
