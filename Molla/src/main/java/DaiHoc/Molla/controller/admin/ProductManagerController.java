package DaiHoc.Molla.controller.admin;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import DaiHoc.Molla.Utils.Constant;
import DaiHoc.Molla.entity.Category;
import DaiHoc.Molla.entity.Manufacturer;
import DaiHoc.Molla.entity.Product;
import DaiHoc.Molla.entity.PromotionalEvent;
import DaiHoc.Molla.entity.SubPicture;
import DaiHoc.Molla.service.IStorageService;
import DaiHoc.Molla.service.ISubPictureService;
import DaiHoc.Molla.service.Imp.CategoryService;
import DaiHoc.Molla.service.Imp.ManufacturerService;
import DaiHoc.Molla.service.Imp.ProductService;
import DaiHoc.Molla.service.Imp.PromotionalEventService;

@Controller
@RequestMapping("/admin")
public class ProductManagerController {

	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ManufacturerService manufacturerService;
	@Autowired
	private ProductService productService;
	@Autowired
	private ISubPictureService subService;
	@Autowired
	private IStorageService iStorageService;
	@Autowired
	private PromotionalEventService promotionalEventService;
	@Autowired
	private UserDetailsService userDetailsService;

	@GetMapping("/product")
	public String ProductMangerPage(Model model, Principal principal, @Param("keyword") String keyword,
			@Param("cateid") Long cateid, @Param("manuid") Long manuid,
			@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {

		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("account", userDetails);

		List<Category> listCate = this.categoryService.findAll();
		model.addAttribute("listCate", listCate);

		List<Manufacturer> listManu = this.manufacturerService.findAll();
		model.addAttribute("listManu", listManu);

		Page<Product> list = productService.getAll(pageNo);

		if (keyword != null) {
			list = productService.searchProduct(keyword, pageNo);
			model.addAttribute("keyword", keyword);
		}
		if (manuid != null && cateid != null) {
			if (manuid == 0 && cateid == 0) {
				list = productService.getAll(pageNo);
			} else if (manuid != 0 && cateid != 0) {
				Manufacturer manu = manufacturerService.findOne(manuid);

				Category cate = categoryService.findOne(cateid);
				model.addAttribute("manuid", manuid);
				model.addAttribute("cateid", cateid);

				list = productService.getAllByCategoryAndManufacturer(cate, manu, pageNo);
			} else if (manuid == 0) {
				Category cate = categoryService.findOne(cateid);
				model.addAttribute("cate", cate);
				list = productService.getAllByCategory(cate, pageNo);
				model.addAttribute("manuid", manuid);
				model.addAttribute("cateid", cateid);
			}

			else if (cateid == 0) {
				Manufacturer manu = manufacturerService.findOne(manuid);
				list = productService.getAllByManufacturer(manu, pageNo);
				model.addAttribute("manuid", manuid);
				model.addAttribute("cateid", cateid);
			}
		}

		model.addAttribute("list", list);
		model.addAttribute("totalPage", list.getTotalPages());
		model.addAttribute("curPage", pageNo);

		return "/admin/views/product/ProductManager";
	}

	@GetMapping("/create-product")
	public String createProduct(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		List<Category> listCate = this.categoryService.findAll();
		model.addAttribute("listCate", listCate);
		List<PromotionalEvent> pro_event = promotionalEventService.findAll();
		model.addAttribute("pro_event", pro_event);
		List<Manufacturer> listManu = this.manufacturerService.findAll();
		model.addAttribute("listManu", listManu);
		return "/admin/views/product/CreateProduct";
	}

	@PostMapping("/create-product")
	public String createCategory(@ModelAttribute("product") Product product,
			@RequestParam("FilePicture") MultipartFile file,
			@RequestParam("FileSubPicture") List<MultipartFile> subFiles) throws IOException {
		iStorageService.store(file);
		String filename = file.getOriginalFilename();
		product.setPicture(filename);
		
		if (product.getEvent().getId()==0)
			product.setEvent(null);
		
		if (productService.create(product)) {
			for (MultipartFile f : subFiles) {
				// Lưu vào database
				SubPicture sub = new SubPicture();
				filename = f.getOriginalFilename();
				sub.setPicture(filename);
				sub.setProduct(product);
				subService.create(sub);
				// Lưu vào subpicture
				iStorageService.setRootLocation(Constant.productSubImageFile);
				iStorageService.store(f, "sub_", 280);
				// Lưu vào zoompicture
				iStorageService.setRootLocation(Constant.productZoomImageFile);
				iStorageService.store(f, "zoom_", 1200);
			}
			iStorageService.store(file, "zoom_", 1200);
			return "redirect:/admin/product";
		}

		return "redirect:/admin/create-product";
	}

	@GetMapping("/update-product/{id}")
	public String editProduct(Model model, @PathVariable("id") Long id) {
		List<Category> listCate = this.categoryService.findAll();

		model.addAttribute("listCate", listCate);
		List<Manufacturer> listManu = this.manufacturerService.findAll();
		model.addAttribute("listManu", listManu);
		List<PromotionalEvent> pro_event = promotionalEventService.findAll();
		model.addAttribute("pro_event", pro_event);
		Product product = productService.findOne(id);
		model.addAttribute("product", product);

		return "admin/views/product/UpdateProduct";
	}

	@PostMapping("/update-product")
	public String updateCategory(@ModelAttribute("product") Product product,
			@RequestParam("FilePicture") MultipartFile file) {
		if (!file.isEmpty()) {
			iStorageService.store(file);
			String filename = file.getOriginalFilename();
			product.setPicture(filename);
		}

		if (productService.update(product)) {
			return "redirect:/admin/product";
		}

		return "redirect:/admin/update-product/" + product.getId();
	}

	@GetMapping("/delete-product/{productId}")
	public String deleteProduct(@ModelAttribute("productId") Long id) {
		if (productService.delete(id)) {
			return "redirect:/admin/product";
		}

		return "redirect:/admin/admin";
	}

}