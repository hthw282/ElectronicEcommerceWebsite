package DaiHoc.Molla.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "lineitem")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LineItem{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name="quantity")
	private int quantity;
	
	@Column(name="subtotal")
	private float subtotal;

	@ManyToOne
	@JoinColumn(name="pro_id", referencedColumnName="id")
	private Product product;
	
	@JsonIgnore
    @OneToOne(mappedBy="lineItem", cascade = CascadeType.DETACH)
    private Cart cart;

	@JsonIgnore
    @OneToOne(mappedBy="lineItem", cascade = CascadeType.DETACH)
    private Transaction transaction;
}
