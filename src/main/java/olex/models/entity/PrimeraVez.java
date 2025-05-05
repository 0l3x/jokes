package olex.models.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "primera_vez")
public class PrimeraVez {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String programa;

    @Column(name = "fecha_emision")
    private LocalDate fechaEmision;

    @OneToOne
    @JoinColumn(name = "idjoke", unique = true)
    private Joke joke;

    @OneToMany(mappedBy = "primeraVez", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Telefono> telefonos;

    // Getters y setters
    public Integer getId() { return id; }

    public String getPrograma() { return programa; }
    public void setPrograma(String programa) { this.programa = programa; }

    public LocalDate getFechaEmision() { return fechaEmision; }
    public void setFechaEmision(LocalDate fechaEmision) { this.fechaEmision = fechaEmision; }

    public Joke getJoke() { return joke; }
    public void setJoke(Joke joke) { this.joke = joke; }

    public List<Telefono> getTelefonos() { return telefonos; }
    
    public void setTelefonos(List<Telefono> telefonos) {
        this.telefonos = telefonos;
        if (telefonos != null) {
            telefonos.forEach(t -> t.setPrimeraVez(this));
        }
    }
}
