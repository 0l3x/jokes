package olex.repository;

import olex.models.entity.Joke;
import olex.models.entity.PrimeraVez;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PrimeraVezRepository extends JpaRepository<PrimeraVez, Integer> {
	
	List<PrimeraVez> findByJoke(Joke joke);
	
}
