package DaiHoc.Molla.entity;

import java.util.List;

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
@Table(name = "manufacturer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Manufacturer {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 500)
    private String name;
    
    @Column(name = "picture", length = 255, nullable = true)
    private String picture;

    @Column(name = "description", length = 500)
    private String description;
	@JsonIgnore
	@OneToMany(mappedBy = "manufacturer", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Product> products;
}
