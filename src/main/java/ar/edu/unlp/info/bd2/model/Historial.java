package ar.edu.unlp.info.bd2.model;
import javax.persistence.*;
import java.util.Date;



@Entity
@Table(name="historial")
public class Historial {
	
	@Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="price")
	private Float precio;
	
	@Column(name="date")
	private Date fecha;
	
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;
	
	
	public Historial () {
		
	}
	public Historial (Float precio, Date fecha, Product product) {
		this.precio=precio;
		this.fecha=fecha;
		this.product= product;
	}
	public Float getPrecio() {
		return precio;
	}
	public void setPrecio(Float precio) {
		
		this.precio = precio;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public Product getProduct() {
		return product;
	}
	
		
}