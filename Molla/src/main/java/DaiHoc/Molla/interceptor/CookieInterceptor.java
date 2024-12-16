package DaiHoc.Molla.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import DaiHoc.Molla.service.IProductService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class CookieInterceptor implements HandlerInterceptor {
	private IProductService service;
	public CookieInterceptor(IProductService service) {
		this.service = service;
	}
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// Thêm cookie vào mỗi yêu cầu
		try {
			Cookie cookie = new Cookie("max_price", service.findMaxPrice().toString());
			cookie.setMaxAge(3600); // Đặt thời gian sống cho cookie (ví dụ: 1 giờ)
			response.addCookie(cookie);
		}
		catch(Exception e) {
			Cookie cookie = new Cookie("max_price", "0");
			cookie.setMaxAge(3600); // Đặt thời gian sống cho cookie (ví dụ: 1 giờ)
			response.addCookie(cookie);
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// Không cần thực hiện gì trong phần này
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// Không cần thực hiện gì trong phần này
	}
}
