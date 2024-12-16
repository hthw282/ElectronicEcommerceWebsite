package DaiHoc.Molla.service.Imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import DaiHoc.Molla.entity.Account;
import DaiHoc.Molla.repository.AccountRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {


    @Autowired
    private AccountRepository repo;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account acc = repo.findByUsername(username);
        if (acc == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new CustomUserDetail(acc);
    }

}
