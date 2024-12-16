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
@Table(name = "transaction")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name="is_review")
	private boolean is_review;
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.DETACH, orphanRemoval = false)
	@JoinColumn(name="line_id", referencedColumnName="id")
	private LineItem lineItem;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="bill_id", referencedColumnName="id")
	private Bill bill;
}
