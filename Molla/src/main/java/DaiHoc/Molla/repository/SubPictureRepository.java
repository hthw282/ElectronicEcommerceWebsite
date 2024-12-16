package DaiHoc.Molla.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import DaiHoc.Molla.entity.SubPicture;

@Repository
public interface SubPictureRepository extends JpaRepository<SubPicture, Long>{
	@Query("SELECT sp FROM SubPicture sp WHERE sp.product.id = :pro_id")
	List<SubPicture> findAllByProductID(Long pro_id);
}
