package DaiHoc.Molla.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import DaiHoc.Molla.entity.Category;
import DaiHoc.Molla.entity.Manufacturer;
import DaiHoc.Molla.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	@Procedure(name = "findProductsByCategoryAndManufacturer")
	List<Product> findProductsByCategoryAndManufacturer(String cate_ids, String manu_ids, float min_price,
			float max_price);

	@Procedure(name = "findTop4Product")
	List<Product> findTop4Product();

	@Procedure(name = "findNewProduct")
	List<Product> findNewProduct();

	@Procedure(name = "findBestSellerProduct")
	List<Product> findBestSellerProduct();

	@Procedure(name = "findOnSaleProduct")
	List<Product> findOnSaleProduct();

	@Procedure(name = "search")
	List<Product> search(String str);

	List<Product> findByCategory(Category category);

	List<Product> findByManufacturer(Manufacturer manu);

	List<Product> findProductsByCategoryAndManufacturer(Category category, Manufacturer manu);

	@Query("SELECT c FROM Product c WHERE c.name LIKE %?1%")
	List<Product> searchProduct(String keyword);

	@Query("SELECT MAX(p.selling_price) FROM Product p")
	Float findMaxPrice();

	List<Product> findByCategory_Id(Long categoryId);

	List<Product> findByManufacturer_Id(Long manufacturerId);
}
