package DaiHoc.Molla.service.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DaiHoc.Molla.entity.Favourite;
import DaiHoc.Molla.entity.Product;
import DaiHoc.Molla.entity.User;
import DaiHoc.Molla.repository.FavouriteRepository;
import DaiHoc.Molla.repository.StockRepository;
import DaiHoc.Molla.service.IFavouriteService;

@Service
public class FavouriteService implements IFavouriteService{
	@Autowired
	private FavouriteRepository repo;
	@Autowired
	private StockRepository stockRepo;
	@Override
	public List<Favourite> findAll() {
		return repo.findAll();
	}

	@Override
	public List<Favourite> findAll(User user) {
		return repo.findByUser(user);
	}

	@Override
	public Favourite findOne(Long id) {
		return repo.findById(id).get();
	}

	@Override
	public Favourite findOne(Product product, User user) {
		return repo.findByProductAndUser(product, user);
	}

	@Override
	public boolean create(Favourite fav) {
		try {
			repo.save(fav);
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Favourite fav) {
		try {
			repo.save(fav);
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Long fav_id) {
		try {
			repo.deleteById(fav_id);;
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean setState(List<Favourite> favs) {
		try {
			for (Favourite fav : favs) {
				if (stockRepo.findAllByProduct_IdAndState(fav.getProduct().getId(), 0).size()>0) {
					fav.setState(0);
				}
				else {
					fav.setState(1);
				}
			}
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
