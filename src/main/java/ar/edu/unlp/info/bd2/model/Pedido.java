package ar.edu.unlp.info.bd2.model;
import javax.persistence.*;

public class Pedido {

	
	private long id;
	
	private int cantidad;

public Pedido() {
		
	}

public Pedido(int cantidad) {
	this.cantidad=cantidad;
}
}
