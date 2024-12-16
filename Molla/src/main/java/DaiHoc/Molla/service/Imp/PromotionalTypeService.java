package DaiHoc.Molla.service.Imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DaiHoc.Molla.entity.PromotionalType;
import DaiHoc.Molla.repository.PromotionalTypeRepository;
import DaiHoc.Molla.service.IPromotionalTypeService;
@Service
public class PromotionalTypeService implements IPromotionalTypeService
{
	@Autowired
	private PromotionalTypeRepository repo;

	@Override
	public Optional<?> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<?> getOne(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public boolean create(Optional<?> object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Optional<?> object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Long id) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
