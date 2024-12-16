package DaiHoc.Molla.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import DaiHoc.Molla.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{
	List<Cart> findByUser_Id(Long user_id);
}
