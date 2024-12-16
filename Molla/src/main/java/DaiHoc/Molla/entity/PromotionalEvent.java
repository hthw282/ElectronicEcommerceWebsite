package DaiHoc.Molla.entity;

import java.sql.Date;
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
@Table(name = "promotional_event")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromotionalEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;
    
    @Column(name = "discount_percent")
    private Float discountPercent;

    @Column(name = "pro_date")
    private Date pro_date;

    @Column(name = "exp_date")
    private Date exp_date;

    @JsonIgnore
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
	private Set<Product> products;
}
