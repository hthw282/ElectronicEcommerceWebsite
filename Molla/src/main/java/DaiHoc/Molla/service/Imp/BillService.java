package DaiHoc.Molla.service.Imp;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DaiHoc.Molla.entity.Bill;
import DaiHoc.Molla.entity.Transaction;
import DaiHoc.Molla.repository.BillRepository;
import DaiHoc.Molla.service.IBillService;

@Service
public class BillService implements IBillService {
	@Autowired
	private BillRepository repo;

	@Override
	public Bill findOne(Long id) {
		return repo.findById(id).get();
	}

	@Override
	public List<Bill> findBillByUserId(Long userId) {
		return repo.findBillByUserId(userId);
	}

	@Override
	public List<Bill> findAll() {
		return repo.findAll();
	}

	@Override
	public Bill create(Bill bill) {
		try {
			Bill savedBill = repo.save(bill);
			return savedBill;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Bill update(Bill object) {
		try {
			new Bill();
			Bill bill = Bill.builder().id(object.getId()).user(object.getUser())
					.product_price(object.getProduct_price()).ship(object.getShip())
					.total_price(object.getTotal_price()).bill_date(object.getBill_date()).state(object.getState())
					.receiver(object.getReceiver()).address_shipment(object.getAddress_shipment())
					.phone_shipment(object.getPhone_shipment()).email(object.getEmail()).note(object.getNote())
					.promotionalCode(object.getPromotionalCode()).build();
			repo.save(bill);
			return bill;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean delete(Long id) {
		try {
			repo.delete(findOne(id));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Map<String, Double> getMonthlyRevenueByYear(int year) {
		List<Bill> paidBills = repo.findByStateIn(Arrays.asList(2, 6));

		Map<String, Double> monthlyRevenue = new LinkedHashMap<>();

		// Khởi tạo giá trị mặc định là 0 cho tất cả các tháng
		for (Month month : Month.values()) {
			String monthName = month.getDisplayName(TextStyle.FULL, new Locale("vi", "VN")) + " " + year;
			monthlyRevenue.put(monthName, 0.0);
		}

		for (Bill bill : paidBills) {
			LocalDate billDate = bill.getBill_date().toLocalDate();
			if (billDate.getYear() == year) {
				String month = billDate.getMonth().getDisplayName(TextStyle.FULL, new Locale("vi", "VN")) + " "
						+ billDate.getYear();
				monthlyRevenue.merge(month, (double) bill.getTotal_price(), Double::sum);
			}
		}

		return monthlyRevenue;
	}

	@Override
	public List<Integer> getAvailableYears() {
		List<Bill> paidBills = repo.findByStateIn(Arrays.asList(2, 6));

		return paidBills.stream().map(bill -> bill.getBill_date().toLocalDate().getYear()).distinct().sorted()
				.collect(Collectors.toList());
	}

	public Map<String, Integer> getSoldProductsStatistics(int month, int year) {
		LocalDate startLocalDate = LocalDate.of(year, month, 1);
		LocalDate endLocalDate = startLocalDate.withDayOfMonth(startLocalDate.lengthOfMonth());
		Date startDate = Date.valueOf(startLocalDate);
		Date endDate = Date.valueOf(endLocalDate);

		List<Bill> bills = repo.findBillsByDateRange(startDate, endDate);
		Map<String, Integer> productStatistics = new HashMap<>();

		for (Bill bill : bills) {
			for (Transaction transaction : bill.getTransactions()) {
				String productName = transaction.getLineItem().getProduct().getName();
				int quantity = transaction.getLineItem().getQuantity();
				productStatistics.merge(productName, quantity, Integer::sum);
			}
		}

		// Sort the productStatistics map by values (quantities) in descending order
		List<Map.Entry<String, Integer>> list = new ArrayList<>(productStatistics.entrySet());
		list.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

		// Convert the sorted list back to a map
		Map<String, Integer> sortedProductStatistics = new LinkedHashMap<>();
		for (Map.Entry<String, Integer> entry : list) {
			sortedProductStatistics.put(entry.getKey(), entry.getValue());
		}

		return sortedProductStatistics;
	}
}
