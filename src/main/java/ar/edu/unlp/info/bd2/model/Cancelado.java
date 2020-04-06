package ar.edu.unlp.info.bd2.model;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import javax.persistence.*;
import org.springframework.context.annotation.Bean;
import javax.persistence.*;

public class Cancelado extends OrderStatus {

	
	public Cancelado () {
		
	}
	
	public Cancelado(Order orden){
		super( orden, "Cancelled");
	
	}
}