package olex.controllers;

import olex.models.entity.Language;
import olex.services.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/languages")
public class LanguageRestController {

    @Autowired
    private LanguageService languageService;

    @GetMapping
    public List<Language> findAll() {
        return languageService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Language> findById(@PathVariable Integer id) {
        Language lang = languageService.findById(id);
        return lang != null ? ResponseEntity.ok(lang) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Language create(@RequestBody Language language) {
        languageService.save(language);
        return language;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Language> update(@PathVariable Integer id, @RequestBody Language updatedLanguage) {
        Language existing = languageService.findById(id);
        if (existing == null) return ResponseEntity.notFound().build();

        existing.setCode(updatedLanguage.getCode());
        existing.setName(updatedLanguage.getName());

        languageService.save(existing);
        return ResponseEntity.ok(existing);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        languageService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
