package ar.edu.unlp.info.bd2.model;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import javax.persistence.*;
import org.springframework.context.annotation.Bean;

@Entity
@Table(name="users")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
	private Long id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="name")
	private String name;
	
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;
	
	@Column(name="fecha")
	private Date fecha;
	
	

	 
	
public User() {
		
	}
	
	
	public User(String email, String password, String userName, String name,  Date fecha) {
		this.name=name;
		this.username=userName;
		this.email=email;
		this.fecha=fecha;
		this.password=password; 
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username=username;
	}
	
	
	public Date getFecha() {
		return fecha;
	}
	
	public void setFecha(Date fecha) {
		this.fecha=fecha;
	}
	
	
	
	public String getName() {
		return name;
	}
		
	public void setName(String name) {
		this.name=name;
	}
	
	
	
	public Long getId() {
		return id;
	}
	
	
	
	public void setPassword(String password) {
		this.password=password;
	}
	
	public String getPassword() {
		return password;
	}

	public void setEmail(String email) {
		// TODO Auto-generated method stub
		this.email=email;
	}


	public String getEmail() {
		// TODO Auto-generated method stub
		return email;
	}



	


	
}
