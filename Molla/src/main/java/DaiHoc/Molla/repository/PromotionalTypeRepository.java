package DaiHoc.Molla.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import DaiHoc.Molla.entity.PromotionalType;
@Repository
public interface PromotionalTypeRepository extends JpaRepository<PromotionalType, Long>{

}
