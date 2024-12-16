package DaiHoc.Molla.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import DaiHoc.Molla.entity.Bill;

@Component
public interface IBillService {
	Bill findOne(Long id);
	List<Bill> findBillByUserId(Long userId);
	List<Bill> findAll();
	
	Bill create(Bill object);
	Bill update(Bill object);
	boolean delete(Long id);
	
	Map<String, Double> getMonthlyRevenueByYear(int year);
	List<Integer> getAvailableYears();
}
