package DaiHoc.Molla.service;

import java.util.List;

import org.springframework.stereotype.Component;

import DaiHoc.Molla.entity.Cart;


@Component
public interface ICartService {
	List<Cart> findAll();
	List<Cart> findByUser(Long user_id);
	Cart findOne(Long id);
	
	Cart create(Long user_id, Long pro_id, int quantity);
	Cart update(Cart cart);
	boolean delete(Long id);
	
	boolean isCartPresent(Long user_id, Long pro_id);
	boolean changeQuantity(Long cart_id, int quantity);
	boolean deleteWithLineItem(Long id);
}
