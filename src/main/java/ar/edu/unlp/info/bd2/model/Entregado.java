package ar.edu.unlp.info.bd2.model;

public class Entregado extends OrderStatus {
	


	public Entregado() {
		
	}
	
	public Entregado(Order orden){
		super( orden, "Delivered");
	
	}
	


}