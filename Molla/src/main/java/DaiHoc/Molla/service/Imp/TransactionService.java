package DaiHoc.Molla.service.Imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DaiHoc.Molla.entity.Transaction;
import DaiHoc.Molla.repository.TransactionRepository;
import DaiHoc.Molla.service.ITransactionService;
@Service
public class TransactionService implements ITransactionService
{
	@Autowired
	private TransactionRepository repo;

	@Override
	public Optional<?> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transaction findOne(Long id) {
		return repo.findById(id).get();
	}

	@Override
	public boolean create(Optional<?> object) {
		try {
			repo.save((Transaction)object.get());
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Optional<?> object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Long id) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public List<Transaction> findTransactionByBillId(Long Id) {
		return repo.findTransactionByBillId(Id);
	}

}
