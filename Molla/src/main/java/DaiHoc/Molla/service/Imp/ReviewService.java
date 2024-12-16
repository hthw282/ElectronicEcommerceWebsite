package DaiHoc.Molla.service.Imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DaiHoc.Molla.entity.Review;
import DaiHoc.Molla.repository.ReviewRepository;
import DaiHoc.Molla.service.IReviewService;
@Service
public class ReviewService implements IReviewService
{
	@Autowired
	private ReviewRepository repo;

	@Override
	public Optional<?> findAll() {
		return Optional.ofNullable(repo.findAll());
	}

	@Override
	public Optional<?> findByProduct_Id(Long pro_id) {
		// TODO Auto-generated method stub
		return Optional.ofNullable(repo.findByProduct_Id(pro_id));
	}

	@Override
	public Optional<?> findOne(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Review create(Review object) {
		return repo.save(object);
	}

	@Override
	public Review update(Review object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Long id) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
