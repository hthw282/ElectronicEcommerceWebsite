package DaiHoc.Molla.service.Imp;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		var authorities = authentication.getAuthorities();
		var roles = authorities.stream().map(r -> r.getAuthority()).findFirst().orElse("");
		// Lấy thông tin người dùng
		CustomUserDetail userDetails = (CustomUserDetail) authentication.getPrincipal();

		// Lấy user_id
		Long userId = userDetails.getAcc().getUser().getId();
		// Tạo session và set attribute user_id

		Cookie cookie = new Cookie("user_id", userId+"");
		cookie.setMaxAge(7 * 24 * 3600); // Đặt thời gian sống cho cookie (ví dụ: 1 giờ)
		response.addCookie(cookie);
		
		if (roles.equals("0")) {
			response.sendRedirect("/admin/user");
		} else if (roles.equals("1")) {
			response.sendRedirect("/");
		} else {
			response.sendRedirect("/error");
		}
	}
}
