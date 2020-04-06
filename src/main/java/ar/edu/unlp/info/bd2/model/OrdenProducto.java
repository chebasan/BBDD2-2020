package ar.edu.unlp.info.bd2.model;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import org.springframework.context.annotation.Bean;
import javax.persistence.*;


@Entity
@Table(name="ordenProducto")
public class OrdenProducto{
	
	@Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@Column
	private Long cantidad;
	
	@OneToOne
	@JoinColumn(name = "cantProducto")
	private Product producto ;
	
	
	@ManyToOne
    @JoinColumn(name="ordenProductoCant")
	private Order orden;
	
	
	public OrdenProducto() {
		
	}
	
	
	
public OrdenProducto(Long cantidad, Product pro, Order order) {
	    this.orden=order;
		this.cantidad=cantidad;
		this.producto=pro;
	}

public Long getCantidad() {
	return cantidad;
}

public void setCantidad(Long cantidad) {
	this.cantidad = cantidad;
}


	
}