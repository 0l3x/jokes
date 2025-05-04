package olex.controllers;

import olex.dto.JokeDTO;
import olex.models.entity.Joke;
import olex.repository.CategoryRepository;
import olex.repository.JokeRepository;
import olex.repository.LanguageRepository;
import olex.repository.TypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/jokes")
public class JokeRestController {

    @Autowired
    private JokeRepository jokeRepository;
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private TypeRepository typeRepository;
    
    @Autowired
    private LanguageRepository languageRepository;

    // GET all jokes as DTOs
    @GetMapping
    public List<JokeDTO> getAllJokes() {
        return jokeRepository.findAll().stream()
                .map(JokeDTO::new)
                .collect(Collectors.toList());
    }

    // GET one joke as DTO
    @GetMapping("/{id}")
    public JokeDTO getJokeById(@PathVariable Integer id) {
        Joke joke = jokeRepository.findById(id).orElseThrow();
        return new JokeDTO(joke);
    }

    // POST - create a new joke and return as DTO
    @PostMapping	
    public Joke createJoke(@RequestBody Joke joke) {
        joke.setCategory(categoryRepository.findById(joke.getCategory().getId()).orElse(null));
        joke.setType(typeRepository.findById(joke.getType().getId()).orElse(null));
        joke.setLanguage(languageRepository.findById(joke.getLanguage().getId()).orElse(null));
        return jokeRepository.save(joke);
    }


    // PUT - update existing joke and return updated DTO
    @PutMapping("/{id}")
    public JokeDTO updateJoke(@PathVariable Integer id, @RequestBody Joke jokeDetails) {
        Joke joke = jokeRepository.findById(id).orElseThrow();
        joke.setText1(jokeDetails.getText1());
        joke.setText2(jokeDetails.getText2());
        joke.setCategory(jokeDetails.getCategory());
        joke.setType(jokeDetails.getType());
        joke.setLanguage(jokeDetails.getLanguage());
        Joke updated = jokeRepository.save(joke);
        return new JokeDTO(updated);
    }

    @DeleteMapping("/{id}")
    public void deleteJoke(@PathVariable Integer id) {
        jokeRepository.deleteById(id);
    }
}
