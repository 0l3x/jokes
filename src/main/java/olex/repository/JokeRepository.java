package olex.repository;

import olex.models.entity.Joke;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JokeRepository extends JpaRepository<Joke, Integer> {

	Optional<Joke> findById(Long id);
    // Aquí podrás luego añadir métodos personalizados (por ejemplo: búsqueda por texto)

	void deleteById(Long id);
}
