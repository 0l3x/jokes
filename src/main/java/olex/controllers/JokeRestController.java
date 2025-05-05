package olex.controllers;

import olex.dto.JokeCreateRequest;
import olex.dto.JokeDTO;
import olex.models.entity.Joke;
import olex.repository.CategoryRepository;
import olex.repository.JokeRepository;
import olex.repository.LanguageRepository;
import olex.repository.TypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    public ResponseEntity<List<JokeDTO>> getAllJokes() {
        List<JokeDTO> jokes = jokeRepository.findAll().stream()
                .map(JokeDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(jokes);
    }

    // GET one joke as DTO
    @GetMapping("/{id}")
    public ResponseEntity<JokeDTO> getJokeById(@PathVariable Integer id) {
        Joke joke = jokeRepository.findById(id)
        		.orElseThrow(() -> new ResponseStatusException(
        			    HttpStatus.NOT_FOUND, "Joke no encontrado con ID: " + id));
        return ResponseEntity.ok(new JokeDTO(joke));
    }

    // POST - create a new joke and return as DTO
    @PostMapping
    public ResponseEntity<?> createJoke(@RequestBody JokeCreateRequest dto) {
        if (dto.getText1() == null || dto.getText2() == null) {
            return ResponseEntity.badRequest().body("Los campos text1 y text2 son obligatorios.");
        }

        Joke joke = new Joke();
        joke.setText1(dto.getText1());
        joke.setText2(dto.getText2());
        joke.setCategory(categoryRepository.findById(dto.getCategory()).orElse(null));
        joke.setType(typeRepository.findById(dto.getType()).orElse(null));
        joke.setLanguage(languageRepository.findById(dto.getLanguage()).orElse(null));

        Joke saved = jokeRepository.save(joke);
        return ResponseEntity.status(HttpStatus.CREATED).body(new JokeDTO(saved));
    }

    // PUT - update existing joke and return updated DTO
    @PutMapping("/{id}")
    public ResponseEntity<?> updateJoke(@PathVariable Integer id, @RequestBody JokeCreateRequest dto) {
        return jokeRepository.findById(id).map(joke -> {
            joke.setText1(dto.getText1());
            joke.setText2(dto.getText2());
            joke.setCategory(categoryRepository.findById(dto.getCategory()).orElse(null));
            joke.setType(typeRepository.findById(dto.getType()).orElse(null));
            joke.setLanguage(languageRepository.findById(dto.getLanguage()).orElse(null));
            Joke updated = jokeRepository.save(joke);
            return ResponseEntity.ok(new JokeDTO(updated));
        }).orElseThrow(() -> new RuntimeException("No se ha podido actualizar el joke con ID: " + id));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteJoke(@PathVariable Integer id) {
        if (!jokeRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede eliminar: joke con ID " + id + " no encontrado.");
        }
        jokeRepository.deleteById(id);
        return ResponseEntity.ok("Joke eliminado correctamente.");
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<JokeDTO>> searchJokes(@RequestParam String text) {
        List<JokeDTO> results = jokeRepository.findByText1ContainingIgnoreCase(text).stream()
                .map(JokeDTO::new)
                .collect(Collectors.toList());

        if (results.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }

        return ResponseEntity.ok(results);
    }

}
