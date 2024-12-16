package DaiHoc.Molla.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import DaiHoc.Molla.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{
	Account findByUsername(String username);

	Account findAccountByUserId(Long userId);
	@Query("SELECT a FROM Account a WHERE a.user.email = :email")
    Account findByUserEmail(@Param("email") String email);
}
