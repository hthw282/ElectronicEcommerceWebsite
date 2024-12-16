package DaiHoc.Molla.controller.admin;

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

import DaiHoc.Molla.entity.Category;
import DaiHoc.Molla.service.Imp.CategoryService;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class CategoryManagerController {
	@Autowired
	private CategoryService categoryService;
	@GetMapping("/category")
	public String CategoryMangerPage(Model model,@Param("keyword")String keyword
			,@RequestParam(name="pageNo", defaultValue="1") Integer pageNo) {
		Page<Category> list = categoryService.findAll(pageNo);
		

		if(keyword != null) {
			list = categoryService.searchCategory(keyword,pageNo);
			model.addAttribute("keyword", keyword);
		}
		
		model.addAttribute("totalPage", list.getTotalPages());
		model.addAttribute("curPage", pageNo);
		model.addAttribute("list", list);
		return "/admin/views/category/CategoryManager";
	}
	
	
	@GetMapping("/create-category")
	public String addCategory(Model model) {
		Category category = new Category();
		model.addAttribute("category", category);
		return "/admin/views/category/CreateCategory";
	}
	
	
	@PostMapping("/create-category")
	public String createCategory(@ModelAttribute("category") Category category ) {
		if(categoryService.create(category)) {
			return "redirect:/admin/category";
		}
		
		return "redirect:/admin/create-category";
	}
	
	@GetMapping("/update-category/{id}")
	public String editCategory(Model model, @PathVariable("id") Long id) {
		Category category = categoryService.findOne(id);
		model.addAttribute("category", category);
		return "admin/views/category/UpdateCategory";		
	}
	
	@PostMapping("/update-category")
	public String updateCategory(@ModelAttribute("category") Category category) {
		if(categoryService.update(category)) {
			return "redirect:/admin/category";
		}
		
		return "redirect:/admin/update-category";
	}
	
	@GetMapping("/delete-category/{id}")
	public String deleteCategory(@ModelAttribute("id") Long id) {
		if(categoryService.delete(id)) {
			return "redirect:/admin/category";
		}
		
		return "redirect:/admin/admin";
	}
}