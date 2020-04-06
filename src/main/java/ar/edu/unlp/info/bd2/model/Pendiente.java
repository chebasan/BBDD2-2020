package ar.edu.unlp.info.bd2.model;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import javax.persistence.*;
import org.springframework.context.annotation.Bean;
import javax.persistence.*;


public class Pendiente extends OrderStatus {

	public Pendiente() {
		
	}
	
	public Pendiente(Order orden){
		super( orden, "Pending");
	
	}
	

	
}