package DaiHoc.Molla.service;

import java.util.Optional;

import org.springframework.stereotype.Component;

import DaiHoc.Molla.entity.Review;


@Component
public interface IReviewService {
	Optional<?> findAll();
	Optional<?> findByProduct_Id(Long pro_id);
	Optional<?> findOne(Long id);
	Review create(Review object);
	Review update(Review object);
	boolean delete(Long id);
}
