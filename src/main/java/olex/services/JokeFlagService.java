package olex.services;

import olex.models.entity.Flag;
import olex.models.entity.Joke;
import olex.models.entity.JokeFlag;
import olex.models.entity.JokeFlagId;
import olex.repository.FlagRepository;
import olex.repository.JokeFlagRepository;
import olex.repository.JokeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JokeFlagService {

    @Autowired
    private JokeFlagRepository jokeFlagRepository;

    @Autowired
    private JokeRepository jokeRepository;

    @Autowired
    private FlagRepository flagRepository;

    /**
     * Devuelve todos los flags asociados a un chiste por su ID.
     */
    public List<Flag> findFlagsByJokeId(Integer jokeId) {
        return jokeFlagRepository.findByJokeId(jokeId)
                .stream()
                .map(JokeFlag::getFlag)
                .toList();
    }

    /**
     * Añade una relación entre un chiste y un flag.
     */
    public void addFlagToJoke(Integer jokeId, Integer flagId) {
        Joke joke = jokeRepository.findById(jokeId).orElseThrow();
        Flag flag = flagRepository.findById(flagId).orElseThrow();

        JokeFlag jf = new JokeFlag();
        jf.setJoke(joke);
        jf.setFlag(flag);

        jokeFlagRepository.save(jf);
    }

    /**
     * Elimina una relación entre un chiste y un flag.
     */
    public void removeFlagFromJoke(Integer jokeId, Integer flagId) {
        JokeFlagId id = new JokeFlagId();
        id.setJoke(jokeId);
        id.setFlag(flagId);
        jokeFlagRepository.deleteById(id);
    }
}
