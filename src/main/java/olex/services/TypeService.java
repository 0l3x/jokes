package olex.services;

import olex.models.entity.Joke;
import olex.models.entity.Type;
import olex.repository.JokeRepository;
import olex.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.List;

@Service
public class TypeService {

    @Autowired
    private TypeRepository typeRepository;
    
    @Autowired
    private JokeRepository jokeRepository;

    public List<Type> findAll() {
        return typeRepository.findAll();
    }

    public Type findById(Integer id) {
        return typeRepository.findById(id).orElse(null);
    }
    
    @Transactional
    public void save(Type type) {
        typeRepository.save(type);
    }
    
    @Transactional
    public void deleteById(Integer id) {
        Type type = typeRepository.findById(id).orElse(null);
        if (type != null) {
            List<Joke> jokes = jokeRepository.findByType(type);
            for (Joke joke : jokes) {
                joke.setType(null);
                jokeRepository.save(joke);
            }
            typeRepository.deleteById(id);
        }
    }

}
