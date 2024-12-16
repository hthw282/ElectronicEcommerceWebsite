package DaiHoc.Molla.service.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DaiHoc.Molla.entity.LineItem;
import DaiHoc.Molla.repository.LineItemRepository;
import DaiHoc.Molla.service.ILineItemService;

@Service
public class LineItemService implements ILineItemService {
	@Autowired
	private LineItemRepository repo;

	@Override
	public List<LineItem> findAll() {
		return repo.findAll();
	}
	
	@Override
	public LineItem findOne(Long id) {
		return repo.findById(id).get();
	}

	@Override
	public LineItem create(LineItem object) {
		try {
			repo.save(object);
			return object;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public LineItem update(LineItem object) {
		try {
			repo.save(object);
			return object;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean delete(Long id) {
		try {
			repo.deleteById(id);;
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
