package DaiHoc.Molla.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import DaiHoc.Molla.entity.Manufacturer;


@Component
public interface IManufacturerService {
	// Tìm kiếm
	List<Manufacturer> findAll();

	Page<Manufacturer> findAll(Integer pageNo);

	List<Manufacturer> searchCategory(String keyword);

	Page<Manufacturer> searchCategory(String keyword, Integer pageNo);

	Manufacturer findOne(Long id);

	// Tác vụ
	boolean create(Manufacturer object);

	boolean update(Manufacturer object);

	boolean delete(Long id);
}
