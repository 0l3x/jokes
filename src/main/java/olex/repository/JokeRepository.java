package olex.repository;

import olex.models.entity.Category;
import olex.models.entity.Joke;
import olex.models.entity.Language;
import olex.models.entity.Type;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JokeRepository extends JpaRepository<Joke, Integer> {

	Optional<Joke> findById(Long id);

	void deleteById(Long id);

	List<Joke> findByCategory(Category category);

	List<Joke> findByLanguage(Language language);

	void save(Language joke);

	List<Joke> findByType(Type type);
	
	List<Joke> findByText1ContainingIgnoreCase(String text);
	
	// 0.5p
	@Query("SELECT j FROM Joke j WHERE j.primeraVez IS NULL")
	List<Joke> findAllWithoutPrimeraVez();

	
}
