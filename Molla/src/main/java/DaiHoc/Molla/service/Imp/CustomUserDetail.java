package DaiHoc.Molla.service.Imp;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import DaiHoc.Molla.entity.Account;
import lombok.Data;
@Data
public class CustomUserDetail implements UserDetails {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Account acc;

    public CustomUserDetail(Account acc) {
        this.acc = acc;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String role = String.valueOf(acc.getRole());
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getPassword() {
        return acc.getPassword();
    }

    @Override
    public String getUsername() {
        return acc.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
