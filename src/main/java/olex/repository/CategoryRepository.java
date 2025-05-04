package olex.repository;

import olex.models.entity.Category;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	Optional<Category> findById(Integer categoryId);

	void deleteById(Integer id);}
