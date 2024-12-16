package DaiHoc.Molla.service.Imp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import DaiHoc.Molla.Utils.Constant;
import DaiHoc.Molla.entity.Category;
import DaiHoc.Molla.entity.Manufacturer;
import DaiHoc.Molla.entity.Product;
import DaiHoc.Molla.entity.Review;
import DaiHoc.Molla.repository.CategoryRepository;
import DaiHoc.Molla.repository.ManufacturerRepository;
import DaiHoc.Molla.repository.ProductRepository;
import DaiHoc.Molla.repository.PromotionalEventRepository;
import DaiHoc.Molla.service.IProductService;
import jakarta.transaction.Transactional;

@Service
public class ProductService implements IProductService {
	@Autowired
	private ProductRepository repo;
	@Autowired
	private ManufacturerRepository manuRepo;
	@Autowired
	private CategoryRepository cateRepo;
	@Autowired
	private PromotionalEventRepository eventRepo;

	// Tìm kiếm
	@Override
	public List<Product> findAll() {
		return repo.findAll();
	}

	@Override
	@Transactional
	public List<Product> findPage(List<Product> products, int sortby, int page) {
		// Xác định các tùy chọn phân trang
		PageRequest pageable = PageRequest.of(page, Constant.productPerPage);

		// Sắp xếp danh sách products dựa trên sortby
		if (sortby == Constant.eSortby.ASCENDING.ordinal()) {
			products.sort(Comparator.comparing(Product::getName));
		} else if (sortby == Constant.eSortby.POPULARITY.ordinal()) {
			products.sort(Comparator.comparing(Product::getSold).reversed());
		} else if (sortby == Constant.eSortby.RATING.ordinal()) {
			products.sort(Comparator.comparing(Product::getRating).reversed());
		} else if (sortby == Constant.eSortby.DATE.ordinal()) {
			products.sort(Comparator.comparing(Product::getId).reversed());
		}

		// Tính toán phân trang
		int start = (int) pageable.getOffset();
		int end = Math.min((start + pageable.getPageSize()), products.size());

		// Tạo đối tượng Page<Product>
		Page<Product> productPage = new PageImpl<>(products.subList(start, end), pageable, products.size());

		return productPage.getContent();
	}

	@Override
	@Transactional
	public List<Product> findAll(String str_cate, String str_manu, float min_price, float max_price) {
		return repo.findProductsByCategoryAndManufacturer(str_cate, str_manu, min_price, max_price);
	}

	@Override
	@Transactional
	public List<Product> findTop4Product() {
		return repo.findTop4Product();
	}

	@Override
	public List<Product> findByCategory(Long cateID) {
		return repo.findByCategory_Id(cateID);
	}

	@Override
	public List<Product> findByManufacturer(Long manuID) {
		return repo.findByManufacturer_Id(manuID);
	}

	@Override
	@Transactional
	public List<Product> findNewProduct() {
		return repo.findNewProduct();
	}

	@Override
	@Transactional
	public List<Product> findBestSellerProduct() {
		return repo.findBestSellerProduct();
	}

	@Override
	@Transactional
	public List<Product> findOnSaleProduct() {
		return repo.findOnSaleProduct();
	}

	@Override
	public Float findMaxPrice() {
		return repo.findMaxPrice();
	}

	@Override
	public Product findOne(Long id) {
		return repo.findById(id).get();
	}

	@Override
	@Transactional
	public List<Product> search(String search) {
		return repo.search(search);
	}

	// Tác vụ
	@Override
	public boolean create(Product object) {
		try {
			repo.save(object);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Product object) {
		try {
			Product product = findOne(object.getId());
			product.setName(object.getName());
			product.setDescription(object.getDescription());
			product.setPicture(object.getPicture());
			product.setPurchase_price(object.getPurchase_price());
			product.setSelling_price(object.getSelling_price());
			product.setState(object.getState());
			product.setManufacturer(manuRepo.findById(object.getManufacturer().getId()).get());
			product.setCategory(cateRepo.findById(object.getCategory().getId()).get());
			product.setEvent(eventRepo.findById(object.getEvent().getId()).get());

			repo.save(product);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Long id) {
		try {
			repo.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Product> getProductInEvent(Long id) {
		List<Product> listAll = repo.findAll();
		List<Product> list = new ArrayList<Product>();
		for (Product product : listAll) {
			if (product.getEvent() != null)
				if (product.getEvent().getId().equals(id)) {
					list.add(product);
				}
		}
		return list;
	}

	@Override
	public List<Product> getProductNotInEvent(List<Product> listAll) {
		List<Product> list = new ArrayList<Product>();
		for (Product product : listAll) {
			if (product.getEvent() == null) {
				list.add(product);
			}
		}
		return list;
	}

	@Override
	public boolean updateEventNull(Product product) {
		product.setEvent(null);
		try {
			Product opt = findOne(product.getId());
			if (opt != null) {
				repo.save(product);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Product> getAllByCategoryAndManufacturerNoPage(Category category, Manufacturer manu) {
		return this.findProductsByCategoryAndManufacturer(category, manu);

	}

	@Override
	public int count(Optional<?> products) {
		@SuppressWarnings("unchecked")
		List<Product> items = (List<Product>) products.get();
		return items.size();
	}

	public int calculatePage(List<Product> products) {
		if (products.size() % Constant.productPerPage != 0) {
			return products.size() / Constant.productPerPage + 1;
		} else {
			return products.size() / Constant.productPerPage;
		}
	}

	@Override
	public List<Product> findProductsByCategoryAndManufacturer(Category category, Manufacturer manu) {
		return repo.findProductsByCategoryAndManufacturer(category, manu);
	}

	@Override
	public Page<Product> getAllByManufacturer(Manufacturer manu, Integer pageNo) {
		List<Product> list = this.findByManufacturer(manu.getId());
		Pageable pageable = PageRequest.of(pageNo - 1, 4);
		Integer start = (int) pageable.getOffset();

		Integer end = (int) ((pageable.getOffset() + pageable.getPageSize()) > list.size() ? list.size()
				: pageable.getOffset() + pageable.getPageSize());
		list = list.subList(start, end);

		return new PageImpl<Product>(list, pageable, this.findByManufacturer(manu.getId()).size());
	}

	@Override
	public Page<Product> getAllByCategory(Category category, Integer pageNo) {
		List<Product> list = this.findByCategory(category.getId());
		Pageable pageable = PageRequest.of(pageNo - 1, 4);
		Integer start = (int) pageable.getOffset();

		Integer end = (int) ((pageable.getOffset() + pageable.getPageSize()) > list.size() ? list.size()
				: pageable.getOffset() + pageable.getPageSize());
		list = list.subList(start, end);

		return new PageImpl<Product>(list, pageable, this.findByCategory(category.getId()).size());
	}

	@Override
	public Page<Product> getAllByCategoryAndManufacturer(Category category, Manufacturer manu, Integer pageNo) {
		List<Product> list = this.findProductsByCategoryAndManufacturer(category, manu);
		Pageable pageable = PageRequest.of(pageNo - 1, 4);
		Integer start = (int) pageable.getOffset();

		Integer end = (int) ((pageable.getOffset() + pageable.getPageSize()) > list.size() ? list.size()
				: pageable.getOffset() + pageable.getPageSize());
		list = list.subList(start, end);

		return new PageImpl<Product>(list, pageable, this.findProductsByCategoryAndManufacturer(category, manu).size());
	}

	@Override
	public List<Product> searchProduct(String keyword) {
		return repo.searchProduct(keyword);
	}

	@Override
	public Page<Product> getAll(Integer pageNo) {
		Pageable pageable = PageRequest.of(pageNo - 1, 4);
		return repo.findAll(pageable);
	}

	@Override
	public Page<Product> searchProduct(String keyword, Integer pageNo) {
		List<Product> list = this.searchProduct(keyword);
		Pageable pageable = PageRequest.of(pageNo - 1, 4);
		Integer start = (int) pageable.getOffset();
		Integer end = (int) ((pageable.getOffset() + pageable.getPageSize()) > list.size() ? list.size()
				: pageable.getOffset() + pageable.getPageSize());
		list = list.subList(start, end);

		return new PageImpl<Product>(list, pageable, this.searchProduct(keyword).size());
	}

	@Override
	public void updateRating(Product product) {
		int temp = 0;
		for (Review rv : product.getReview()) {
			temp += rv.getRating();
		}
		product.setRating(temp / product.getReview().size());
		repo.save(product);
	}

}
