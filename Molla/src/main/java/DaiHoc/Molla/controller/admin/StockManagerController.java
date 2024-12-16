package DaiHoc.Molla.controller.admin;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import DaiHoc.Molla.entity.Product;
import DaiHoc.Molla.entity.Stock;
import DaiHoc.Molla.service.IProductService;
import DaiHoc.Molla.service.IStockService;

@Controller
@RequestMapping("/admin")
public class StockManagerController {
	@Autowired
	private IStockService stockService;
	@Autowired
	private IProductService proService;

	@GetMapping("/stock")
	public String getStock(ModelMap model) {
		List<Product> products = proService.findAll();
		model.addAttribute("products", products);
		return "/admin/views/stock/list";
	}

	@GetMapping("/add-stock")
	public String getAddStock(@RequestParam Long pro_id, ModelMap model) {
		model.addAttribute("product", proService.findOne(pro_id));
		
		List<Stock> stocks = stockService.findAllByProductId(pro_id);
        Collections.sort(stocks, Comparator.comparing(Stock::getState));
		model.addAttribute("stocks", stocks);
		return "/admin/views/stock/add-stock";
	}

	@PostMapping("/add-stock")
	public String postAddStock(@RequestParam Long pro_id, @RequestParam int quantity, ModelMap model) {
		for (int i=0;i<quantity;i++) {
			new Stock();
			Stock stock = Stock.builder().product(proService.findOne(pro_id)).state(0).build();
			stockService.create(stock);
		}
		
		return "redirect:add-stock?pro_id="+pro_id;
	}

	@PostMapping("/update-stock")
	public String postUpdateStock(@RequestParam Long stock_id, @RequestParam int state, ModelMap model) {
		Stock stock = stockService.findOne(stock_id);
		stock.setState(state);
		stockService.update(stock);
		
		return "redirect:add-stock?pro_id="+stock.getProduct().getId();
	}

}