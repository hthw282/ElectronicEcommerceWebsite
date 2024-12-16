package DaiHoc.Molla.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import DaiHoc.Molla.entity.Category;
import DaiHoc.Molla.entity.Manufacturer;
import DaiHoc.Molla.entity.Product;
import DaiHoc.Molla.entity.PromotionalEvent;
import DaiHoc.Molla.service.Imp.CategoryService;
import DaiHoc.Molla.service.Imp.ManufacturerService;
import DaiHoc.Molla.service.Imp.ProductService;
import DaiHoc.Molla.service.Imp.PromotionalEventService;

@Controller
@RequestMapping("/admin")
public class PromotionalEventController {
	@Autowired
	private PromotionalEventService promotionalEventService;
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ManufacturerService manufacturerService;
	
	@GetMapping("promotional-event")
	public String PromotionalEventManager(Model model) {
		List<PromotionalEvent> pro_event = promotionalEventService.findAll();
		model.addAttribute("pro_event", pro_event);
		return  "admin/views/DiscountEvent/PromotionalEventManager";
		
	}
	@GetMapping("/create-promotional-event")
	public String addPromotionalEvent(Model model) {
		PromotionalEvent event = new PromotionalEvent();
		model.addAttribute("event", event);
		return "/admin/views/DiscountEvent/CreateEvent";
	}
	@PostMapping("/create-promotional-event")
	public String createCategory(@ModelAttribute("event") PromotionalEvent event ) {
		if(promotionalEventService.create(event)) {
			return "redirect:/admin/promotional-event";
		}
		
		return "redirect:/admin/create-category";
	}
	@GetMapping("/delete-promotional-event/{id}")
	public String deletePromotionalEvent(@ModelAttribute("id") Long id) {
		if(promotionalEventService.delete(id)) {
			return "redirect:/admin/promotional-event";
		}
		
		return "redirect:/admin/promotional-event";
	}
	@GetMapping("/update-promotional-event/{id}")
	public String editPromotionalEvent(Model model, @PathVariable("id") Long id) {
		PromotionalEvent event = promotionalEventService.findOne(id);
		model.addAttribute("event", event);
		return "admin/views/DiscountEvent/UpdateEvent";		
	}
	@PostMapping("/update-promotional-event")
	public String updatePromotionalEvent(@ModelAttribute("event") PromotionalEvent event ) {
		if(promotionalEventService.update(event)) {
			return "redirect:/admin/promotional-event";
		}
		
		return "redirect:/admin/promotional-event";
	}

	@GetMapping("/update-product-event/{id}")
	public String editProductEvent(Model model, @PathVariable("id") Long id) {
		PromotionalEvent event = promotionalEventService.findOne(id);
		model.addAttribute("event", event);
		List<Product> listIn=productService.getProductInEvent(id);
		model.addAttribute("listin",listIn);
		return "admin/views/DiscountEvent/addProduct";		
	}
	
	
	@GetMapping("/delete-product-event/{id}") 		
		public String updateProductEvent(@PathVariable("id") Long id) {
		    Product product = productService.findOne(id);
		    Long eventId=product.getEvent().getId();
		    if (productService.updateEventNull(product)) {
		        return "redirect:/admin/update-product-event/" + eventId;
		    }
		    return "redirect:/admin/views/DiscountEvent/PromotionalEventManager";
		
	}


	@GetMapping("/add-product-event/{id}")
	public String addProductEvent(Model model,@Param("keyword") String keyword, @PathVariable("id") Long id,
			@Param("cateid") Long cateid,@Param("manuid") Long manuid) {
		PromotionalEvent event = promotionalEventService.findOne(id);
		model.addAttribute("event", event);
		
		List<Category> listCate = this.categoryService.findAll();	
		model.addAttribute("listCate", listCate);
		
		List<Manufacturer> listManu = this.manufacturerService.findAll();
		model.addAttribute("listManu", listManu);		
		
		List<Product> list= productService.findAll();	
		if (keyword != null) {
			list = productService.searchProduct(keyword);
			model.addAttribute("keyword", keyword);
		}
		if(manuid != null && cateid != null) {
			if(manuid == 0 && cateid == 0) {
				list = productService.findAll();
				
			}
			else if(manuid != 0 && cateid != 0) {
				Manufacturer manu = manufacturerService.findOne(manuid);
				
				Category cate = categoryService.findOne(cateid);
				model.addAttribute("manuid", manuid);
				model.addAttribute("cateid", cateid);
				
				list = productService.getAllByCategoryAndManufacturerNoPage(cate, manu);
			}
			else if (manuid == 0) {
				Category cate = categoryService.findOne(cateid);
				model.addAttribute("cate", cate);
				list = productService.findByCategory(cate.getId());
				model.addAttribute("manuid", manuid);
				model.addAttribute("cateid", cateid);
			}
			
			else if (cateid == 0 ) {
				Manufacturer manu = manufacturerService.findOne(manuid);
				list = productService.findByManufacturer(manu.getId());
				model.addAttribute("manuid", manuid);
				model.addAttribute("cateid", cateid);
			}
		}
			
		List<Product> listnot=productService.getProductNotInEvent(list);
		model.addAttribute("listnot",listnot);

		return "admin/views/DiscountEvent/addToEvent";		
	}
	
	@PostMapping("/add-product-to-event/{eventId}/{id}")
	public String addProductEvent(@PathVariable("eventId") Long eventId, @PathVariable("id") Long productId) {
	    Product product = productService.findOne(productId);
	    product.setEvent(promotionalEventService.findOne(eventId));
	    if (productService.update(product)) {
	        return "redirect:/admin/add-product-event/" + eventId; 
	    }
	        	
	    return "redirect:/admin/update-product-event/" + eventId;
	}
}
