package DaiHoc.Molla.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "promotional_type")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromotionalType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "name", length = 500)
	private String name;

	@JsonIgnore
	@OneToMany(mappedBy = "type", cascade = CascadeType.ALL)
	private Set<PromotionalCode> promotionalCodes;
}
