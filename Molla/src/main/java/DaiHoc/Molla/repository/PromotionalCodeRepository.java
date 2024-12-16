package DaiHoc.Molla.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import DaiHoc.Molla.entity.PromotionalCode;
@Repository
public interface PromotionalCodeRepository extends JpaRepository<PromotionalCode, Long>{

}
