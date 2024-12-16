package DaiHoc.Molla.service.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DaiHoc.Molla.entity.SupportMessage;
import DaiHoc.Molla.repository.SupportMessageRepository;
import DaiHoc.Molla.service.ISupportMessageService;

@Service
public class SupportMessageService implements ISupportMessageService {
	@Autowired
	private SupportMessageRepository repo;

	@Override
	public List<SupportMessage> findAll() {
		return repo.findAll();
	}

	@Override
	public SupportMessage findOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SupportMessage> findAllByAnswerIsNull() {
		return repo.findAllByAnswerIsNull();
	}

	@Override
	public List<SupportMessage> findAllByAnswerIsNotNull() {
		return repo.findAllByAnswerIsNotNull();
	}

	@Override
	public boolean create(SupportMessage object) {
		try {
			repo.save(object);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(SupportMessage object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
