package olex.repository;

import olex.models.entity.Telefono;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TelefonoRepository extends JpaRepository<Telefono, Integer> {
    List<Telefono> findByPrimeraVezId(Integer primeraVezId);
}
