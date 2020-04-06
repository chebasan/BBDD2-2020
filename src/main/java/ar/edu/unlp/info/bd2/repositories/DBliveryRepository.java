package ar.edu.unlp.info.bd2.repositories;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import ar.edu.unlp.info.bd2.model.*;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
import java.util.Optional;

 //import org.hibernate.SessionFactory; 




import java.util.List;
import java.util.Optional;

 @Repository
public class DBliveryRepository {
	 @Autowired
	 LocalSessionFactoryBean session; 
     	
	 public void createUser(User newUser){
		// TODO Auto-generated method stub
		 this.aSession().save(newUser); 
		 }
	 
	  public void createProduct(Product newProduct) {
	     // TODO Auto-generated method stub
		 this.aSession().save(newProduct); 
		}
	
	  public void createSupplier(Supplier newSuplier) {
			// TODO Auto-generated method stub
			 this.aSession().save(newSuplier); 
			
		}
	
		public void createOrder(Order newOrder) {
			// TODO Auto-generated method stub
			 this.aSession().save(newOrder); 
		}

	  private Session aSession(){
	        return session.getObject().getCurrentSession();
	    }
	    
	
	    
	    
	    
	    private User getUserByCriteria(String aCriteria,Object aCriteriaArg) {
	    	 User u = (User) this.aSession().createQuery("select u " + "from User u " + "where u."+aCriteria+"=:"+aCriteria+"").
		                setParameter(aCriteria, aCriteriaArg).getSingleResult();
	    	return u;
	    }
	    
	    
	    public Optional<User> getUserByAId(Long id) {
	    	 User u=this.getUserByCriteria("id", id);
	    	 Optional<User> ret = Optional.of(u);
	         return ret;
	    }
	    
	    public Optional<User> getUserByEmail(String email){
	        User u= this.getUserByCriteria("email", email);
	        Optional<User> ret = Optional.of(u);
	         return ret;
	    }

		public Optional<User> getUserByUsername(String username) {
			 User u= this.getUserByCriteria("username", username);
		        Optional<User> ret = Optional.of(u);
		         return ret;
		}

		public Optional<Product> getProductById(Long id) {
			Product p= (Product) this.aSession().createQuery("select p " + "from Product p " + "where p.id=:id").
	                setParameter("id", id).getSingleResult();
			  Optional<Product> ret = Optional.ofNullable(p);
		        return ret;
		}

		public Optional<Order> getOrderById(Long id) {		
			Order o= (Order) this.aSession().createQuery("select o " + "from Order o " + "where o.id=:id").
	                setParameter("id", id).getSingleResult();
			  Optional<Order> ret = Optional.ofNullable(o);
		        return ret;
		}

		
		public List<Product> getProductByName(String name) {
			// TODO Auto-generated method stub
			  return this.aSession().createQuery("select p " + "from Product p " + "where p.name like CONCAT('%',:name,'%')").setParameter("name", name).list();							  
	              
		}
        
		@Transactional
		public void addOrderDeliver(Long id,User delivery) {
			this.aSession().createQuery("update Orders p  " + "set p.delivery=:"+delivery+ "where p.id=:"+id+"").executeUpdate();
	
			
		}

	
	    
}
