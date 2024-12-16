package DaiHoc.Molla.service;

import java.util.List;

import org.springframework.stereotype.Component;

import DaiHoc.Molla.entity.LineItem;


@Component
public interface ILineItemService {
	List<LineItem> findAll();
	LineItem findOne(Long id);
	LineItem create(LineItem object);
	LineItem update(LineItem object);
	boolean delete(Long id);
}
