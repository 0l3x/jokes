package olex.models.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "telefonos")
public class Telefono {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String numero;

    @ManyToOne
    @JoinColumn(name = "idprimeravez", nullable = false)
    private PrimeraVez primeraVez;

    // Getters y setters
    public Integer getId() { return id; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public PrimeraVez getPrimeraVez() { return primeraVez; }
    public void setPrimeraVez(PrimeraVez primeraVez) { this.primeraVez = primeraVez; }
}
