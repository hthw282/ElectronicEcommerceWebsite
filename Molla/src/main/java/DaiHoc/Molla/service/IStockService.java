package DaiHoc.Molla.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import DaiHoc.Molla.entity.Stock;

@Component
public interface IStockService {
	List<Stock> findAll();
	List<Stock> findAllByProductId(Long id);
	Stock findOne(Long id);
	Stock create(Stock object);
	Stock update(Stock object);
	boolean delete(Long id);
}
