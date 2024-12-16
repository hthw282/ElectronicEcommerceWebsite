package DaiHoc.Molla.service.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DaiHoc.Molla.entity.PromotionalEvent;
import DaiHoc.Molla.repository.PromotionalEventRepository;
import DaiHoc.Molla.service.IPromotionalEventService;

@Service
public class PromotionalEventService implements IPromotionalEventService {
	@Autowired
	private PromotionalEventRepository repo;

	@Override
	public List<PromotionalEvent> findAll() {
		return repo.findAll();
	}

	@Override
	public PromotionalEvent findOne(Long id) {
		return repo.findById(id).get();
	}

	@Override
	public boolean create(PromotionalEvent object) {
		try {
			repo.save(object);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(PromotionalEvent object) {
		try {
			PromotionalEvent event = findOne(object.getId());
			event.setName(object.getName());
			event.setDiscountPercent(object.getDiscountPercent());
			event.setExp_date(object.getExp_date());
			event.setPro_date(object.getPro_date());

			repo.save(event);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
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

}
