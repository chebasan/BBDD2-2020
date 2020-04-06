package ar.edu.unlp.info.bd2.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import javax.persistence.*;


@Entity
@Table(name="product")
public class Product {

	@Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="weight")
	private Float peso;
	
	@Column(name="price")
	private Float precio;
	
	@ManyToOne
	@JoinColumn(name="supplier_id")
	private Supplier supplier;
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name = "product_id")
	private List<Historial> historial = new ArrayList<>();
	
	
	  @OneToOne
	  @JoinColumn(name = "cantProducto")
	  private OrdenProducto cantProducto; 

	 
	
public Product() {
		
	}
public Product(String name, Float precio, Float peso, Supplier suplier) {
	
	this.name=name;
	this.peso=peso;
	this.precio=precio;
	this.supplier=suplier;
	this.addHistorial(new Historial( precio, new Date(), this));
	

}





public String getName() {
	return name;
}

public void setame(String name) {
	this.name=name;
}

public Float getPrice() {
	return precio;
}

public Float getPrecio() {
	return precio;
}


public void setPrecio(Float precio, Date startDate) {
	this.precio=precio;
	this.addHistorial(new Historial( precio, startDate, this));
}

public Long getId() {
	return id;
}

public Float getPeso() {
	return peso;
}

public void setPeso(Float peso) {
	this.peso=peso;
}


public List<Historial> getHistorial() {
    return historial;
}

public void addHistorial(Historial aHistorial){
    this.getHistorial().add(aHistorial);
    this.precio= aHistorial.getPrecio();
}


public List<Historial> getPrices() {
	// TODO Auto-generated method stub
	return this.getHistorial();
}



}