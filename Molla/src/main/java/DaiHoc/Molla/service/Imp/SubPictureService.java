package DaiHoc.Molla.service.Imp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DaiHoc.Molla.entity.SubPicture;
import DaiHoc.Molla.repository.SubPictureRepository;
import DaiHoc.Molla.service.ISubPictureService;
@Service
public class SubPictureService implements ISubPictureService{
	@Autowired
	private SubPictureRepository repo;
	@Override
	public Optional<?> getAllByProducID(Long pro_id) {
		return Optional.ofNullable(repo.findAllByProductID(pro_id));
	}
	@Override
	public SubPicture create(SubPicture sub) {
		return repo.save(sub);
	}

}
