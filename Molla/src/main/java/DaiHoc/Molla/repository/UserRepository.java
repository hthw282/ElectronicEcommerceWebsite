package DaiHoc.Molla.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import DaiHoc.Molla.entity.Account;
import DaiHoc.Molla.entity.Category;
import DaiHoc.Molla.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	@Query("SELECT c FROM User c WHERE c.fullname LIKE %?1%")
	List<User> searchUser(String keyword);
}
