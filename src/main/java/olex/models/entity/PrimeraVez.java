package olex.models.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "primera_vez")
public class PrimeraVez {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String programa;

    @Column(name = "fecha_emision", nullable = false)
    private LocalDate fechaEmision;

    @OneToOne
    @JoinColumn(name = "idjoke", unique = true)
    private Joke joke;

    // Getters y setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDate fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Joke getJoke() {
        return joke;
    }

    public void setJoke(Joke joke) {
        this.joke = joke;
    }
}
