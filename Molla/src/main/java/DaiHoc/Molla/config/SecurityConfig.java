package DaiHoc.Molla.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import DaiHoc.Molla.service.Imp.CustomSuccessHandler;
import DaiHoc.Molla.service.Imp.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	CustomSuccessHandler customSuccessHandler;
	
	@Autowired
	CustomUserDetailsService customUserDetailsService;

	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http.csrf(c -> c.disable())
		.authorizeHttpRequests(request -> request
				.requestMatchers("/admin/**").hasAuthority("0")
				.anyRequest().permitAll())	//đoạn này tạm thời xử lí vậy, nào phân quyền cho custumer thì sửa lại sau nha
				/*.anyRequest().authenticated())*/
		
		.formLogin(form -> form.loginPage("/login").loginProcessingUrl("/login")
				.successHandler(customSuccessHandler)
				.permitAll())
		.sessionManagement(session -> session
				.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
				.invalidSessionUrl("/login?expired")
				.maximumSessions(100)
				.sessionRegistry(sessionRegistry())
				.maxSessionsPreventsLogin(true))
		.logout(form -> form.invalidateHttpSession(true).clearAuthentication(true)
				.deleteCookies("max_price")
				.deleteCookies("user_id")
				.deleteCookies("JSESSIONID")
				.invalidateHttpSession(true)
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/home").permitAll())
				
		
		.rememberMe(rememberMe -> rememberMe.key("uniqueAndSecretKey")
				.tokenValiditySeconds(7 * 24 * 3600)); //1 tuần
		
		return http.build();
		
	}

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }
	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
	}
}