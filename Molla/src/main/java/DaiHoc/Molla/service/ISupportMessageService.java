package DaiHoc.Molla.service;

import java.util.List;

import org.springframework.stereotype.Component;

import DaiHoc.Molla.entity.SupportMessage;

@Component
public interface ISupportMessageService {
	List<SupportMessage> findAll();

	SupportMessage findOne(Long id);
	
	List<SupportMessage> findAllByAnswerIsNull();
	
	List<SupportMessage> findAllByAnswerIsNotNull();

	boolean create(SupportMessage object);

	boolean update(SupportMessage object);

	boolean delete(Long id);
}
