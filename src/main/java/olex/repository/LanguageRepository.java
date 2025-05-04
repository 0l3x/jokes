package olex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import olex.models.entity.Language;

public interface LanguageRepository extends JpaRepository<Language, Integer> {
}
