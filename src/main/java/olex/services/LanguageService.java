package olex.services;

import olex.models.entity.Language;

import java.util.List;

public interface LanguageService {
    List<Language> findAll();
    Language findById(Integer id);
}
