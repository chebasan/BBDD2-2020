package ar.edu.unlp.info.bd2.services;
import ar.edu.unlp.info.bd2.model.*;

import ar.edu.unlp.info.bd2.repositories.*;
import ar.edu.unlp.info.bd2.repositories.DBliveryException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
// AYUDA MEMORIA DE LO QUE HACE LA INTERFAZ  
// Interfaz: es una especie de forma que obliga a objetos de diferentes tipos a implementar ciertos metodos.. se usa con la palabra clave implements
//esto obliga a el objeto a implementar cierto codigo de esa interfaz.. Un objeto implementa las funcionalidades de dicha interfaz, es parecido
//al polimorfismo pero son 2 objetos que no estan jerarquizados pero implementan la misma interfaz 


@Service
public class DBliveryServiceImpl implements DBliveryService {
    
	@Autowired
    DBliveryRepository repository;
     
	public DBliveryServiceImpl(DBliveryRepository repository) {
		this.repository=repository;
	}   
	
	@Override
    @Transactional 
    public User createUser(String email, String password, String username, String name, Date dateOfBirth) {
        User newUser=new User(email, password, username, name, dateOfBirth);
        repository.createUser(newUser); 
        return newUser;
    }
	
	
	

	
	@Override
	public Optional<Product> getProductById(Long id) {
		
		return repository.getProductById(id);
	}
	
	
	@Override
	public List<Product> getProductByName(String name) {
		// TODO Auto-generated method stub
		return repository.getProductByName(name);
	}
	
	
	@Override
	public Optional<Order> getOrderById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	@Transactional
	public Order addProduct (Long order,Long quantity, Product product )throws DBliveryException{
		// TODO Auto-generated method stub
		  Optional<Order> anOrder= repository.getOrderById(order);
	        if(anOrder.isPresent()) {
	             Order orden= anOrder.get();	              
	             orden.addProduct(quantity, product);
	             return orden;	            
	        }else{
	            throw new DBliveryException("The Order don't exist.");
	        }
	  }
	
	
	
	/**
	 * Registra el envío del pedido, registrando al repartidor y cambiando su estado a Send.
	 * @param order pedido a ser enviado
	 * @param deliveryUser Usuario que entrega el pedido
	 * @return el pedido modificado
	 * @throws DBliveryException en caso de no existir el pedido, que el pedido no se encuentre en estado Pending o sí no contiene productos.
	 */
	
	@Override
	@Transactional
	public Order deliverOrder(Long order, User deliveryUser) throws DBliveryException {
		// TODO Auto-generated method stub
		   if(this.canDeliver(order)) {
			 
	        	 Optional<Order> anOrder= repository.getOrderById(order);	            
	             Order orden= anOrder.get();	              
	             orden.sendOrder();
	             orden.addDelivery(deliveryUser);
             	             	          	             
	             return orden;	            
	        }else{
	            throw new DBliveryException("The Order don't exist, is not Pending, or is not have Products.");
	        }
	}
	
	
	
	@Override
	@Transactional
	public Order cancelOrder(Long order) throws DBliveryException {
	
		// TODO Auto-generated method stub
		  
	        if(this.canCancel(order)) {
	        	 Optional<Order> anOrder= repository.getOrderById(order);
	             Order orden= anOrder.get();	              
	             orden.cancelOrder();
	             return orden;	            
	        }else{
	            throw new DBliveryException("The Order don't exist, or is not Pending.");
	        }
		
	}
	
	
	@Override
	public Order finishOrder(Long order) throws DBliveryException {
		
		 if(this.canFinish(order)) {
        	 Optional<Order> anOrder= repository.getOrderById(order);
             Order orden= anOrder.get();	              
             orden.finishOrder();
             return orden;	            
        }else{
            throw new DBliveryException("The Order don't exist, or is not Pending.");
        }
		
	}
	
	
	
	@Override
	public boolean canCancel(Long order) throws DBliveryException {
		// TODO Auto-generated method stub
		 Optional<Order> anOrder= repository.getOrderById(order);
	        if(anOrder.isPresent()) {
	          
	        	Order orden= anOrder.get();	              
	            Object orS= orden.getStatusActual();
	         
	             if(orS instanceof Pendiente) { return true;  }
	             return false;
	        }else{
	            throw new DBliveryException("The Order don't exist.");
	        }
	        		
	}
	
	
	@Override
	public boolean canFinish(Long id) throws DBliveryException {
		Optional<Order> anOrder= repository.getOrderById(id);
        if(anOrder.isPresent()) {
          
        	Order orden= anOrder.get();	              
            Object orS= orden.getStatusActual();
         
             if(orS instanceof Enviado) { return true;  }
            
             return false;
        }else{
            throw new DBliveryException("The Order don't exist.");
        }
	}
	
	
	

	@Override
	public boolean canDeliver(Long order) throws DBliveryException {
		// TODO Auto-generated method stub
				 Optional<Order> anOrder= repository.getOrderById(order);
			        if(anOrder.isPresent()) {
			          
			        	Order orden= anOrder.get();	              
			            Object orS= orden.getStatusActual();
			          
			             if(orS instanceof Pendiente && orden.haveProducts()) { return true;  }
			             return false;
			        }else{
			            throw new DBliveryException("The Order is not pending.");
			        }
	}
	
	
	
	
	@Override
	public OrderStatus getActualStatus(Long order) {
		// TODO Auto-generated method stub
		 Optional<Order> anOrder= repository.getOrderById(order);
	     Order orden= anOrder.get();	              
		 
		return (OrderStatus) orden.getStatusActual();
	}
	
	
	
	
	@Override
	public Optional<User> getUserById(Long id){
		 return repository.getUserByAId(id);
	}
	@Override
	public Optional<User> getUserByEmail(String email){
		return repository.getUserByEmail(email);
	}
	
	public Optional<User> getUserByUsername(String username){
		return repository.getUserByUsername(username);
	}
	
	
	  @Override
	  @Transactional
	    public Product createProduct(String name, Float price, Float weight, Supplier supplier) {
	        Product newProduct= new Product(name, price, weight, supplier);
	        repository.createProduct(newProduct); 
	        return newProduct;
	    }
	  
	  @Override
	  @Transactional
	    public Order createOrder(Date dateOfOrder, String address, Float coordX, Float coordY, User client) {
	        Order newOrder= new Order(dateOfOrder, address, coordX, coordY, client);
	        repository.createOrder(newOrder); 
	        return newOrder;
	    }
	  
	 
	  
	  @Override
	  @Transactional
	    public Supplier createSupplier(String name, String cuil, String address, Float coordX, Float coordY) {
	        Supplier newSupplier= new Supplier(name, cuil, address, coordX, coordY);
	        repository.createSupplier(newSupplier); 
	        return newSupplier;
	    }
	  
	
	
    
	  @Override
	  @Transactional
	    public Product updateProductPrice(Long id, Float price, Date startDate) throws DBliveryException {
	        Optional<Product> aProduct= repository.getProductById(id);
	        if(aProduct.isPresent()) {
	             Product pro= aProduct.get();	              
	             pro.setPrecio(price, startDate);
	             return pro;	            
	        }else{
	            throw new DBliveryException("The product don't exist.");
	        }
	    }
	    
	  
	  
	   
	  
   
   
}