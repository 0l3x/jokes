package olex.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import olex.models.entity.Flag;
import olex.models.entity.Joke;
import olex.models.entity.JokeFlag;
import olex.models.entity.JokeFlagId;
import olex.repository.FlagRepository;
import olex.repository.JokeFlagRepository;
import olex.repository.JokeRepository;

import java.util.List;

@RestController
@RequestMapping("/api/jokesflags")
public class JokeFlagRestController {

    @Autowired
    private JokeFlagRepository jokeFlagRepository;

    @Autowired
    private JokeRepository jokeRepository;

    @Autowired
    private FlagRepository flagRepository;

    // Obtiene todos los enlaces joke-flag
    @GetMapping
    public List<JokeFlag> findAll() {
        return jokeFlagRepository.findAll();
    }

    // Crea una relación
    @PostMapping
    public JokeFlag create(@RequestParam Integer jokeId, @RequestParam Integer flagId) {
        JokeFlag jf = new JokeFlag();
        jf.setJoke(jokeRepository.findById(jokeId).orElseThrow());
        jf.setFlag(flagRepository.findById(flagId).orElseThrow());
        return jokeFlagRepository.save(jf);
    }

    // Elimina una relación
    @DeleteMapping
    public void delete(@RequestParam Integer jokeId, @RequestParam Integer flagId) {
        JokeFlagId id = new JokeFlagId();
        id.setJoke(jokeId);
        id.setFlag(flagId);
        jokeFlagRepository.deleteById(id);
    }
}
