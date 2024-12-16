package DaiHoc.Molla.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import DaiHoc.Molla.entity.LineItem;

@Repository
public interface LineItemRepository extends JpaRepository<LineItem, Long>{
	Optional<LineItem> findFirstByOrderByIdDesc();
}
