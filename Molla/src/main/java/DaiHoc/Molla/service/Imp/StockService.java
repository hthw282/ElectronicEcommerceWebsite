package DaiHoc.Molla.service.Imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DaiHoc.Molla.entity.Product;
import DaiHoc.Molla.entity.Stock;
import DaiHoc.Molla.repository.StockRepository;
import DaiHoc.Molla.service.IStockService;
@Service
public class StockService implements IStockService
{
	@Autowired
	private StockRepository repo;

	@Override
	public List<Stock> findAll() {
		return repo.findAll();
	}

	@Override
	public List<Stock> findAllByProductId(Long id) {
		return repo.findAllByProduct_Id(id);
	}

	@Override
	public Stock findOne(Long id) {
		return repo.findById(id).get();
	}

	@Override
	public Stock create(Stock object) {
		return repo.save(object);
	}

	@Override
	public Stock update(Stock object) {
		Stock stock = findOne(object.getId());
		stock.setState(object.getState());
		repo.save(stock);
		return stock;
	}

	@Override
	public boolean delete(Long id) {
		try {
			repo.deleteById(id);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}

}
