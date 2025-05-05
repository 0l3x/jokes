package olex.dto;

import java.time.LocalDate;
import java.util.List;

public class PrimeraVezDTO {
	private String programa;
    private LocalDate fechaEmision;
    private Integer idJoke;
    private List<String> telefonos;
    
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
	public Integer getIdJoke() {
		return idJoke;
	}
	public void setIdJoke(Integer idJoke) {
		this.idJoke = idJoke;
	}
	public List<String> getTelefonos() {
		return telefonos;
	}
	public void setTelefonos(List<String> telefonos) {
		this.telefonos = telefonos;
	}
    
    
}
