package DaiHoc.Molla.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import DaiHoc.Molla.interceptor.CookieInterceptor;
import DaiHoc.Molla.service.IProductService;
@Configuration
public class WebConfig implements WebMvcConfigurer{
	private IProductService service;
	public WebConfig(IProductService service) {
		this.service = service;
	}
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CookieInterceptor(service)).addPathPatterns("/**");
    }
}
