package DaiHoc.Molla.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import DaiHoc.Molla.entity.Favourite;
import DaiHoc.Molla.entity.Product;
import DaiHoc.Molla.entity.User;

@Component
public interface IFavouriteService {
	List<Favourite> findAll();

	List<Favourite> findAll(User user);

	Favourite findOne(Long id);

	Favourite findOne(Product product, User user);

	boolean create(Favourite fav);

	boolean update(Favourite fav);

	boolean delete(Long fav_id);

	boolean setState(List<Favourite> favs);
}
