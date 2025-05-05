package olex.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import olex.models.entity.Flag;
import olex.models.entity.JokeFlag;
import olex.models.entity.JokeFlagId;

@Repository
public interface JokeFlagRepository extends JpaRepository<JokeFlag, JokeFlagId> {

	void deleteByJokeId(Long id);
	
    List<JokeFlag> findByJokeId(Integer jokeId);
}
