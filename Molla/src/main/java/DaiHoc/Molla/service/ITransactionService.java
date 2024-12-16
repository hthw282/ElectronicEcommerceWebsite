package DaiHoc.Molla.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import DaiHoc.Molla.entity.Transaction;


@Component
public interface ITransactionService {
	Optional<?> getAll();
	Transaction findOne(Long id);
	boolean create(Optional<?> object);
	boolean update(Optional<?> object);
	boolean delete(Long id);
	List<Transaction> findTransactionByBillId(Long Id);
	
}
