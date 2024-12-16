package DaiHoc.Molla.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import DaiHoc.Molla.entity.SupportMessage;

@Repository
public interface SupportMessageRepository extends JpaRepository<SupportMessage, Long> {
	List<SupportMessage> findAllByAnswerIsNull();

	List<SupportMessage> findAllByAnswerIsNotNull();
}
