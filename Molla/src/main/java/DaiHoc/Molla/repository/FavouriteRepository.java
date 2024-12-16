package DaiHoc.Molla.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import DaiHoc.Molla.entity.Favourite;
import DaiHoc.Molla.entity.Product;
import DaiHoc.Molla.entity.User;

@Repository
public interface FavouriteRepository extends JpaRepository<Favourite, Long>{
	List<Favourite> findByUser(User user);
	Favourite findByProductAndUser(Product product, User user);
}
