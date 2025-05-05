package olex.services;

import olex.models.entity.Flag;
import olex.models.entity.Joke;
import olex.repository.FlagRepository;
import olex.repository.JokeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlagService {
    @Autowired
    private FlagRepository flagRepository;
    
    @Autowired
    private JokeRepository jokeRepository;

    public List<Flag> findAll() {
        return flagRepository.findAll();
    }

    public Flag findById(Integer id) {
        return flagRepository.findById(id).orElse(null);
    }

    public void save(Flag flag) {
        flagRepository.save(flag);
    }

    public void deleteById(Integer id) {
        Flag flag = flagRepository.findById(id).orElse(null);
        if (flag != null) {
            // Desasociar flag de todos los jokes
            List<Joke> jokes = jokeRepository.findAll();
            for (Joke joke : jokes) {
                if (joke.getFlags().contains(flag)) {
                    joke.getFlags().remove(flag);
                    jokeRepository.save(joke); // guardar cambios
                }
            }
            flagRepository.delete(flag);
        }
    }
}
