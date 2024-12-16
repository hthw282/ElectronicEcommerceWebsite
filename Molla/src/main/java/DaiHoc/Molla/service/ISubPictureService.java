package DaiHoc.Molla.service;

import java.util.Optional;

import org.springframework.stereotype.Component;

import DaiHoc.Molla.entity.SubPicture;

@Component
public interface ISubPictureService {
	Optional<?> getAllByProducID(Long pro_id);

	SubPicture create(SubPicture sub);
}
