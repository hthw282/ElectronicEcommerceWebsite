package DaiHoc.Molla.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import DaiHoc.Molla.entity.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long>{
    List<Stock> findAllByProduct_IdAndState(Long productId, int state);
    List<Stock> findAllByProduct_Id(Long productId);
}
