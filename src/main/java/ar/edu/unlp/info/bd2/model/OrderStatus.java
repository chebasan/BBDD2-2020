package ar.edu.unlp.info.bd2.model;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import javax.persistence.*;
import org.springframework.context.annotation.Bean;
import javax.persistence.*;

@Entity
@Table(name="ordenEstados")
public class   OrderStatus{
	
	@Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@ManyToOne
	@JoinColumn(name="ordenStatus")
	private Order ord; 

	@Column(name="estadoActual")
	private String estadoActual; 
	
   public OrderStatus(){
		
	}
	
	 public OrderStatus(Order ord, String estadoActual){
		this.setOrder(ord);
		this.setEstadoActual(estadoActual);

    }
	 
	 public void setEstadoActual(String estadoActual) {
		 this.estadoActual=estadoActual;
	 }
	
	 public String getEstadoActual() {
		 return estadoActual;
	 }
     
	 public Order getOrder(){
		 return ord;
	 }
     
	 public void setOrder(Order ord) {
		 this.ord=ord;
	 }

	public String getStatus() {
		// TODO Auto-generated method stub
		return estadoActual;
	}
	 
}
