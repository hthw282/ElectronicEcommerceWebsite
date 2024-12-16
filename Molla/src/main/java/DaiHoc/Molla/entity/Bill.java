package DaiHoc.Molla.entity;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bill")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Bill {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "product_price")
	private float product_price;
	
	@Column(name = "ship")
	private float ship;
	
	@Column(name = "total_price")
	private float total_price;
	
	@Column(name = "bill_date")
	private Date bill_date;
	
	@Column(name = "state")
	private int state;
	
	@Column(name = "receiver")
	private String receiver;
	
	@Column(name = "address_shipment")
	private String address_shipment;
	
	@Column(name = "phone_shipment")
	private String phone_shipment;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "note")
	private String note;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="user_id", referencedColumnName="id")
	private User user;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="promotional_codeid", referencedColumnName="id")
	private PromotionalCode promotionalCode;
	
	@JsonIgnore
	@OneToMany(mappedBy="bill", cascade = CascadeType.ALL)
	private List<Transaction> transactions;
}
