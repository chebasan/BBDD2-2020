package ar.edu.unlp.info.bd2.model;
import javax.persistence.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;



@Entity
@Table(name="orders")
public class Order{
	
	
	@Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@Column(name="dateOfOrder")
	private Date fecha;
	
	
	@Column(name="address")
	private String adress;
	
	@Column(name="coordx")
	private Float coorx;
	
	@Column(name="coordy")
	private Float coory;
	
	

	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="ordenProductoCant")
	private List<OrdenProducto> ordenproductos = new ArrayList<>();
	
	
	
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="ordenStatus")
	private List<OrderStatus> historialEstados = new ArrayList<>();
	

	@OneToOne
    @JoinColumn(name="client")
	private User client;
	
	
	
	@OneToOne
    @JoinColumn(name="delivery")
	private User delivery;
	
	
    public Order() {
		
	}


	public Order(Date fecha, String adress, Float coordX, Float coordY, User client) {
		this.fecha=fecha;
		this.adress=adress;
		this.client=client;
		this.coorx=coordX;
		this.coory=coordY;
		this.setStatus(new Pendiente(this));
		 
	}
	
	
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
		
	
	
	public float getCoorx() {
		return coorx;
	}
	public void setCoorx(Float coorx) {
		this.coorx = coorx;
	}
	public float getCoory() {
		return coory;
	}
	public User getClient() {
		return client;
	}
	
	public void setCoory(Float coory) {
		this.coory = coory;
	}


	public void addProduct(Long quantity, Product product) {
		ordenproductos.add(new OrdenProducto(quantity, product, this));
		
	}
	

	public List<OrdenProducto> getProducts() {
		return ordenproductos;
	}
	
	public boolean haveProducts() {
		return !ordenproductos.isEmpty();
	}
    
	public void setStatus(OrderStatus estadoActual) {
		historialEstados.add(estadoActual);
	}

	public List<OrderStatus> getStatus() {
		
		return historialEstados;
	}


	
	public Object getStatusActual() {
		return historialEstados.get(historialEstados.size() - 1);
	}


	public void cancelOrder() {
		historialEstados.add(new Cancelado(this));
		
	}
    
		

	public void sendOrder() {
		this.setStatus(new Enviado(this));
		
	}
	//public void finOrder() {
	//	this.setStatus(new Entregado(this));
//	}
	public void finishOrder() {
		this.setStatus(new Entregado(this));
	}
     
	public void addDelivery(User deliveryUser) {
		delivery=deliveryUser;
	}
	
	public User getDeliveryUser(){
		return delivery;
	}



	
	
}