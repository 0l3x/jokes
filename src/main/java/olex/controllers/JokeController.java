package olex.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import olex.models.entity.Joke;
import olex.repository.JokeRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/jokes")
public class JokeController {

    @Autowired
    private JokeRepository jokeRepository;

    @GetMapping
    public List<Joke> getAllJokes() {
        return jokeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Joke getJokeById(@PathVariable Integer id) {
        return jokeRepository.findById(id).orElseThrow();
    }

    @PostMapping
    public Joke createJoke(@RequestBody Joke joke) {
        return jokeRepository.save(joke);
    }

    @PutMapping("/{id}")
    public Joke updateJoke(@PathVariable Integer id, @RequestBody Joke jokeDetails) {
        Joke joke = jokeRepository.findById(id).orElseThrow();
        joke.setText1(jokeDetails.getText1());
        joke.setText2(jokeDetails.getText2());
        joke.setCategory(jokeDetails.getCategory());
        joke.setType(jokeDetails.getType());
        joke.setLanguage(jokeDetails.getLanguage());
        return jokeRepository.save(joke);
    }

    @DeleteMapping("/{id}")
    public void deleteJoke(@PathVariable Integer id) {
        jokeRepository.deleteById(id);
    }
}

