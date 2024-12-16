package DaiHoc.Molla.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import DaiHoc.Molla.entity.User;


@Component
public interface IUserService {
	List<User> findAll();
	User findOne(Long id);
	User create(User object);
	boolean update(User object);
	boolean delete(Long id);

	List<User> searchUser(String keyword);
	Page<User> getAll(Integer pageNo);
	Page<User> searchUser(String keyword, Integer pageNo);
}
