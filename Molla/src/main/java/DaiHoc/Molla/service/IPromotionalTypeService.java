package DaiHoc.Molla.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;


@Component
public interface IPromotionalTypeService {
	Optional<?> getAll();
	Optional<?> getOne(Long id);
	boolean create(Optional<?> object);
	boolean update(Optional<?> object);
	boolean delete(Long id);
}
