package DaiHoc.Molla.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import DaiHoc.Molla.entity.Account;
import DaiHoc.Molla.entity.User;
import DaiHoc.Molla.service.IAccountService;
import DaiHoc.Molla.service.IUserService;

@Controller
public class AccountController {
	@Autowired
	private IUserService userService;
	@Autowired
	private IAccountService accountService;
	
	
	@GetMapping({"/register"})
	public String getLoginRegisterPage(@ModelAttribute("account") Account account) {
		return "web/views/register";
	}
	@PostMapping("/register")
	public String saveAccount(@ModelAttribute("account") Account account, Model model) {
		User user = userService.create(new User());
		account.setUser(user);
		accountService.create(account);
		model.addAttribute("registermessage", "Registered Successfully!");
	    return "web/views/register";
	}
	@GetMapping("/login")
	public String login() {
		return "web/views/login";
	}
}
