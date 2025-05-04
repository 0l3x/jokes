package olex.services;

import olex.models.entity.Joke;
import olex.repository.JokeFlagRepository;
import olex.repository.JokeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.List;

@Service
public class JokeService {

    @Autowired
    private JokeRepository jokeRepository;
    
    @Autowired
    private JokeFlagRepository jokeFlagRepository;

    public List<Joke> findAll() {
        return jokeRepository.findAll();
    }

    public Joke findById(Long id) {
        return jokeRepository.findById(id).orElseThrow();
    }

    public void save(Joke joke) {
        jokeRepository.save(joke);
    }
    
    @Transactional
    public void deleteById(Long id) {
    	// Borra relaciones primero
        jokeFlagRepository.deleteByJokeId(id);

        // Luego borra el chiste
        jokeRepository.deleteById(id);
    }
}
