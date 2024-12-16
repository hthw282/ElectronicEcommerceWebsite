package DaiHoc.Molla.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import DaiHoc.Molla.Utils.Constant;
import DaiHoc.Molla.entity.Manufacturer;
import DaiHoc.Molla.service.IStorageService;
import DaiHoc.Molla.service.Imp.ManufacturerService;

@Controller
@RequestMapping("/admin")
public class ManufacturerManagerController {
	@Autowired
	private ManufacturerService manufacturerService;
	@Autowired
	private IStorageService iStorageService ;
	
	@GetMapping("/manufacturer")
	public String ManufacturerMangerPage(Model model) {
		List<Manufacturer> list = manufacturerService.findAll();
		model.addAttribute("list", list);
		return "/admin/views/manufacturer/ManufacturerManager";
	}

	@GetMapping("/create-manufacturer")
	public String addManufacturer(Model model) {
		Manufacturer manufacturer = new Manufacturer();
		model.addAttribute("manufacturer", manufacturer);
		return "/admin/views/manufacturer/CreateManufacturer";
	}

	@PostMapping("/create-manufacturer")
	public String createManufacturer(@ModelAttribute("manufacturer") Manufacturer manufacturer, @RequestParam("FilePicture") MultipartFile file ) {
		iStorageService.setRootLocation(Constant.manuImageFile);
		iStorageService.save(file);

		String filename = file.getOriginalFilename();
		manufacturer.setPicture(filename);
		
		if(manufacturerService.create(manufacturer)) {
			return "redirect:/admin/manufacturer";
		}
		
		return "redirect:/admin/create-manufacturer";
	}

	@GetMapping("/update-manufacturer/{id}")
	public String editManufacturer(Model model, @PathVariable("id") Long id) {
		Manufacturer manufacturer = manufacturerService.findOne(id);
		model.addAttribute("manufacturer", manufacturer);
		return "admin/views/manufacturer/UpdateManufacturer";
	}

	@PostMapping("/update-manufacturer")
	public String updateManufacturer(@ModelAttribute("manufacturer") Manufacturer manufacturer, @RequestParam("FilePicture") MultipartFile file) {
		iStorageService.setRootLocation(Constant.manuImageFile);
		iStorageService.save(file);
			
		String filename = file.getOriginalFilename();
		manufacturer.setPicture(filename);
		
		if(manufacturerService.update(manufacturer)) {
			return "redirect:/admin/manufacturer";
		}
		
		return "redirect:/admin/update-manufacturer";
	}

	@GetMapping("/delete-manufacturer/{id}")
	public String deleteManufacturer(@ModelAttribute("id") Long id) {
		if (manufacturerService.delete(id)) {
			return "redirect:/admin/manufacturer";
		}

		return "redirect:/admin/admin";
	}
}
