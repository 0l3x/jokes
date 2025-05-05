package olex.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "flags")
public class Flag {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Integer id;

 @Column(name = "flag", nullable = false)
 private String name;

 // Getters y setters
 public Integer getId() { return id; }
 public void setId(Integer id) { this.id = id; }

 public String getName() { return name; }
 public void setName(String name) { this.name = name; }
}

