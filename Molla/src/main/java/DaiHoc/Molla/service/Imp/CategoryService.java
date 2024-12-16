package DaiHoc.Molla.service.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import DaiHoc.Molla.entity.Category;
import DaiHoc.Molla.repository.CategoryRepository;
import DaiHoc.Molla.service.ICategoryService;

@Service
public class CategoryService implements ICategoryService {
	@Autowired
	private CategoryRepository repo;

	@Override
	public List<Category> findAll() {
		return repo.findAll();
	}

	@Override
	public Page<Category> findAll(Integer pageNo) {
		Pageable pageable = PageRequest.of(pageNo - 1, 10);
		return repo.findAll(pageable);
	}

	@Override
	public Category findOne(Long id) {
		return repo.findById(id).get();
	}

	@Override
	public List<Category> searchCategory(String keyword) {
		return repo.searchCategory(keyword);
	}

	@Override
	public Page<Category> searchCategory(String keyword, Integer pageNo) {
		List<Category> list = this.searchCategory(keyword);
		Pageable pageable = PageRequest.of(pageNo - 1, 10);
		Integer start = (int) pageable.getOffset();
		Integer end = (int) ((pageable.getOffset() + pageable.getPageSize()) > list.size() ? list.size()
				: pageable.getOffset() + pageable.getPageSize());
		list = list.subList(start, end);

		return new PageImpl<Category>(list, pageable, this.searchCategory(keyword).size());
	}

	@Override
	public boolean create(Category object) {
		try {
			repo.save(object);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Category object) {
		try {
			Category cate = findOne(object.getId());
			cate.setName(object.getName());
			cate.setDescription(object.getDescription());
			
			repo.save(cate);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Long id) {
		try {
			repo.delete(findOne(id));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
