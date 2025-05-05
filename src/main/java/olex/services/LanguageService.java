package olex.services;

import olex.models.entity.Language;
import olex.repository.JokeRepository;
import olex.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.List;

@Service
public class LanguageService {
    
    @Autowired
    private LanguageRepository languageRepository;
    
    @Autowired
    private JokeRepository jokeRepository;

    public List<Language> findAll() {
        return languageRepository.findAll();
    }

    public Language findById(Integer id) {
        return languageRepository.findById(id).orElse(null);
    }

    public void save(Language language) {
        languageRepository.save(language);
    }

    @Transactional
    public void deleteById(Integer id) {
        Language language = languageRepository.findById(id).orElse(null);
        if (language != null) {
            // Desasociar el idioma de todos los chistes
            jokeRepository.findByLanguage(language).forEach(joke -> {
                joke.setLanguage(null);
                jokeRepository.save(joke);
            });

            // eliminar el idioma
            languageRepository.delete(language);
        }
    }
}
