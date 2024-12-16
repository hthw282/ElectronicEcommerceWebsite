package DaiHoc.Molla.service.Imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import DaiHoc.Molla.entity.User;

import DaiHoc.Molla.repository.UserRepository;
import DaiHoc.Molla.service.IUserService;
@Service
public class UserService implements IUserService
{
	@Autowired
	private UserRepository repo;

	@Override
	public List<User> findAll() {
		return repo.findAll();
	}

	@Override
	public User create(User object) {
		User user = repo.save(object);
		return user;
	}


	@Override
	public boolean update(User object) {
		try {
			User user = findOne(object.getId());
			user.setFullname(object.getFullname());
			user.setBirthdate(object.getBirthdate());
			user.setAddress(object.getAddress());
			user.setPhone(object.getPhone());
			user.setEmail(object.getEmail());
			user.setNotify(object.isNotify());
			user.setState(object.getState());
			repo.save(user);
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public boolean delete(Long Id) {
		try {
			repo.delete(findOne(Id));
			return true;
		}
		 catch (Exception e) {
			 e.printStackTrace();
		 }
		 return false;
	}
	
	@Override
	public List<User> searchUser(String keyword) {
		// TODO Auto-generated method stub
		return repo.searchUser(keyword);
	}
	@Override
	public Page<User> getAll(Integer pageNo) {
		Pageable pageable =PageRequest.of(pageNo-1, 10);
		return repo.findAll(pageable);
	}
	@Override
	public Page<User> searchUser(String keyword, Integer pageNo) {
		List list = this.searchUser(keyword);
		Pageable pageable =PageRequest.of(pageNo-1, 10);
		Integer start= (int) pageable.getOffset();
		Integer end = (int)((pageable.getOffset() + pageable.getPageSize()) > list.size() ? list.size() :  pageable.getOffset() + pageable.getPageSize());
		list = list.subList(start, end);
		
		return new PageImpl<User>(list, pageable,  this.searchUser(keyword).size());
	}

	@Override
	public User findOne(Long id) {
		return repo.findById(id).get();
	}

}
