package DaiHoc.Molla.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import DaiHoc.Molla.entity.Category;

@Component
public interface ICategoryService {
	// Tìm kiếm
	List<Category> findAll();

	Page<Category> findAll(Integer pageNo);

	List<Category> searchCategory(String keyword);

	Page<Category> searchCategory(String keyword, Integer pageNo);

	Category findOne(Long id);

	// Tác vụ
	boolean create(Category object);

	boolean update(Category object);

	boolean delete(Long id);
}
