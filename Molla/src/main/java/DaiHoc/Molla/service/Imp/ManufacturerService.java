package DaiHoc.Molla.service.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import DaiHoc.Molla.entity.Manufacturer;
import DaiHoc.Molla.repository.ManufacturerRepository;
import DaiHoc.Molla.service.IManufacturerService;
@Service
public class ManufacturerService implements IManufacturerService
{
	@Autowired
	private ManufacturerRepository repo;

	@Override
	public List<Manufacturer> findAll() {
		return repo.findAll();
	}

	@Override
	public Page<Manufacturer> findAll(Integer pageNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Manufacturer> searchCategory(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Manufacturer> searchCategory(String keyword, Integer pageNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Manufacturer findOne(Long id) {
		return repo.findById(id).get();
	}

	@Override
	public boolean create(Manufacturer object) {
		try {
			repo.save(object);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Manufacturer object) {
		try {
			Manufacturer manu = findOne(object.getId());
			manu.setName(object.getName());
			manu.setDescription(object.getDescription());
			manu.setPicture(object.getPicture());
			
			repo.save(manu);
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
