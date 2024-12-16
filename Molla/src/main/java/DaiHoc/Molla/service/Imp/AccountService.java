package DaiHoc.Molla.service.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import DaiHoc.Molla.Utils.Constant;
import DaiHoc.Molla.entity.Account;
import DaiHoc.Molla.repository.AccountRepository;
import DaiHoc.Molla.service.IAccountService;

@Service
public class AccountService implements IAccountService {
	@Autowired
	private AccountRepository repo;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public List<Account> findAll() {
		return repo.findAll();
	}

	@Override
	public Account findOne(Long id) {
		return repo.findById(id).get();
	}

	@Override
	public Account findAccountByUserId(Long id) {
		return repo.findAccountByUserId(id);
	}

	@Override
	public Account findOne(String username) {
		return repo.findByUsername(username);
	}
	
	@Override
	public Account create(Account account) {
		Account acc = new Account();
		acc.setUsername(account.getUsername());
		acc.setPassword(passwordEncoder.encode(account.getPassword()));
		acc.setRole(Constant.eRole.CUSTOMER.ordinal()); 
		acc.setUser(account.getUser());
		return repo.save(acc);
	}

	@Override
	public Account update(Account object) {
		try {
			Account account = findOne(object.getId());
			account.setPassword(object.getPassword());
			account.setRole(object.getRole());
			repo.save(account);
			return account;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean delete(Long id) {
		try {
			repo.deleteById(id);
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Account findByEmail(String email) {
		return repo.findByUserEmail(email);
	}
}
