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
@RequestMapping("/api/jokeflags")
public class JokeFlagController {

    @Autowired
    private JokeFlagRepository jokeFlagRepository;

    @Autowired
    private JokeRepository jokeRepository;

    @Autowired
    private FlagRepository flagRepository;

    @GetMapping
    public List<JokeFlag> getAll() {
        return jokeFlagRepository.findAll();
    }

    @PostMapping
    public JokeFlag create(@RequestParam Integer jokeId, @RequestParam Integer flagId) {
        Joke joke = jokeRepository.findById(jokeId).orElseThrow();
        Flag flag = flagRepository.findById(flagId).orElseThrow();

        JokeFlag jokeFlag = new JokeFlag();
        jokeFlag.setJoke(joke);
        jokeFlag.setFlag(flag);

        return jokeFlagRepository.save(jokeFlag);
    }

    @DeleteMapping
    public void delete(@RequestParam Integer jokeId, @RequestParam Integer flagId) {
        JokeFlagId id = new JokeFlagId();
        id.setJoke(jokeId);
        id.setFlag(flagId);
        jokeFlagRepository.deleteById(id);
    }
}
