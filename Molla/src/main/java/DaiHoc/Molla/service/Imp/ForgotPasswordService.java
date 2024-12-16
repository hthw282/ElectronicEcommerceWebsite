package DaiHoc.Molla.service.Imp;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import DaiHoc.Molla.entity.ForgotPasswordToken;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class ForgotPasswordService {

	@Autowired
	JavaMailSender javaMailSender;
	
	private final int MINUTES = 10;
	
	public String generateToken() {
		return UUID.randomUUID().toString();
	}
	
	public LocalDateTime expireTimeRange() {
		return LocalDateTime.now().plusMinutes(MINUTES);
	}
	
	public void sendEmail(String to, String subject, String emailLink) throws MessagingException, UnsupportedEncodingException {
		
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		String emailContent = "<p>Xin chào</p>"
							+ "Hãy vào link dưới đây để thay đổi mật khẩu"
		                    + "<p><a href=\"" + emailLink + "\">Thay đổi mật khẩu</a></p>"
							+ "<br>"
							+ "Hãy bỏ qua email này nếu bạn không thực hiện yêu cầu";
		helper.setText(emailContent, true);
		helper.setFrom("hthw282@gmail.com", "Trang web thương mại điện tử Molla");
		helper.setSubject(subject);
		helper.setTo(to);
		javaMailSender.send(message);
		
	}
	
	public boolean isExpired(ForgotPasswordToken forgotPasswordToken) {
		return LocalDateTime.now().isAfter(forgotPasswordToken.getExpireTime());
	}
	
	public String checkValidity (ForgotPasswordToken forgotPasswordToken, Model model) {
		if (forgotPasswordToken == null) {
			model.addAttribute("error", "Token không khả dụng");
			return "web/views/404";
		}
		else if (forgotPasswordToken.isUsed()) {
			model.addAttribute("error", "Token đã được sử dụng");
			return "web/views/error-page";			
		}
		else if (isExpired(forgotPasswordToken)) {
			model.addAttribute("error", "Token đã quá hạn");
			return "web/views/error-page";			
		}
		else {
			return "web/views/reset-password";
		}
	}
}
