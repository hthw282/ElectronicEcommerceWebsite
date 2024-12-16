package DaiHoc.Molla.controller.web;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import DaiHoc.Molla.entity.Account;
import DaiHoc.Molla.entity.ForgotPasswordToken;
import DaiHoc.Molla.entity.User;
import DaiHoc.Molla.repository.ForgotPasswordRepository;
import DaiHoc.Molla.service.Imp.AccountService;
import DaiHoc.Molla.service.Imp.ForgotPasswordService;
import DaiHoc.Molla.service.Imp.UserService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class ForgotPasswordController {

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private ForgotPasswordService forgotPasswordService;
	
	@Autowired
	ForgotPasswordRepository forgotPasswordRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	@GetMapping("/password-request")
	public String passwordRequest() {
		return "web/views/password-request";
	}
	
	@PostMapping("/password-request")
	public String savePasswordRequest(@RequestParam("username") String email, Model model) {
		Account acc = accountService.findByEmail(email);
		if (acc == null) {
			model.addAttribute("error", "Mail này không được đăng kí trước đó");
			return "web/views/password-request";
			
		}
		
		ForgotPasswordToken forgotPasswordToken = new ForgotPasswordToken();
		forgotPasswordToken.setExpireTime(forgotPasswordService.expireTimeRange());
		forgotPasswordToken.setToken(forgotPasswordService.generateToken());
		forgotPasswordToken.setAccount(acc);
		forgotPasswordToken.setUsed(false);
		
		forgotPasswordRepository.save(forgotPasswordToken);
		
		String emailLink = "http://localhost:8081/reset-password?token=" + forgotPasswordToken.getToken();
		
		try {
			forgotPasswordService.sendEmail(acc.getUser().getEmail(), "Liên kết thay đổi mật khẩu:", emailLink);
		} catch (UnsupportedEncodingException | MessagingException e) {
			model.addAttribute("error", "Lỗi trong khi gửi mail");
			return "web/views/password-request";
		}
		
		return "redirect:/password-request?success";
	}
	
	@GetMapping("/reset-password")
	public String resetPassword(@Param(value="token") String token, Model model, HttpSession session) {
		session.setAttribute("token", token);
		ForgotPasswordToken forgotPasswordToken = forgotPasswordRepository.findByToken(token);
		return forgotPasswordService.checkValidity(forgotPasswordToken, model);
	}
	
	@PostMapping("/reset-password")
	public String saveResetPassword(HttpServletRequest request, HttpSession session, Model model) {
	    String password = request.getParameter("password");
	    String token = (String)session.getAttribute("token");
	    
	    ForgotPasswordToken forgotPasswordToken = forgotPasswordRepository.findByToken(token);
	    Account account = forgotPasswordToken.getAccount();

	    // Kiểm tra xem account đã tồn tại trong cơ sở dữ liệu hay chưa
	    if (account != null) {
	        // Cập nhật mật khẩu của account
	        account.setPassword(passwordEncoder.encode(password));
	        accountService.update(account);

	        // Đánh dấu token là đã sử dụng
	        forgotPasswordToken.setUsed(true);
	        forgotPasswordRepository.save(forgotPasswordToken);

	        model.addAttribute("message", "Bạn đã đổi mật khẩu thành công!");
	    } else {
	        model.addAttribute("error", "Không tìm thấy tài khoản");
	    }

	    return "web/views/reset-password";
	}

}
