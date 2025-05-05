package olex.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "types")
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "type")
    private String name; 

    public String getName() {
        return name;
    }
    
    
    public void setName(String type) {
        this.name = type;
    }

	public Integer getId() {
		return id;
	}

}

