package ar.edu.unlp.info.bd2.model;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import org.springframework.context.annotation.Bean;
import javax.persistence.*;

@Entity
@Table(name="supplier")
public class Supplier{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;
	
	@Column(name="name")
	private String name;
	
	
	@Column(name="adress")
	private String adress;
	
	@Column(name="cuil")
	private String cuil;
	
	@Column(name="coordX")
	private Float coordenadax;
	
	@Column(name="coordY")
	private Float coordenaday;
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name = "supplier_id")
	private List<Product> product = new ArrayList<>();
	
	public Supplier() {
		
	}
	//String name, String cuil, String address, Float coordX, Float coordY
	
	public Supplier (String name, String cuil, String adress, Float coorx, Float coory) {
		this.name=name;
		this.coordenadax=coorx;
		this.coordenaday=coory;
		this.cuil=cuil;
		this.adress=adress;
	}
	
	
	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getCuil() {
		return cuil;
	}
	public void setCuil(String cuit) {
		this.cuil = cuil;
	}
	public Float getCoordenadax() {
		return coordenadax;
	}
	public void setCoordenadax(Float coordenadax) {
		this.coordenadax = coordenadax;
	}
	public Float getCoordenaday() {
		return coordenaday;
	}
	public void setCoordenaday(Float coordenaday) {
		this.coordenaday = coordenaday;
	}
	
	
	public List<Product> getProduct() {
	    return product;
	}

	public void addProduct(Product aProduct){
	    this.getProduct().add(aProduct);
	 
	}

	
	
}