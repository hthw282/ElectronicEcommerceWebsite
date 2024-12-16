package DaiHoc.Molla.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import DaiHoc.Molla.entity.Bill;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
	List<Bill> findBillByUserId(Long userId);

	List<Bill> findByState(int state);
    List<Bill> findByStateIn(List<Integer> states);

	@Query("SELECT b FROM Bill b WHERE b.bill_date BETWEEN :startDate AND :endDate")
	List<Bill> findBillsByDateRange(Date startDate, Date endDate);
}
