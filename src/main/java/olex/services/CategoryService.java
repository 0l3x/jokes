package olex.services;

import olex.models.entity.Category;
import olex.models.entity.Joke;
import olex.repository.CategoryRepository;
import olex.repository.JokeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private JokeRepository jokeRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow();
    }

	public Category findById(Integer categoryId) {
		return categoryRepository.findById(categoryId).orElseThrow();
	}

	public void save(Category category) {
		categoryRepository.save(category);
	}
	
	@Transactional
	public void delete(Integer id) {
		Category category = categoryRepository.findById(id).orElse(null);
	    if (category != null) {
	        List<Joke> jokesConCategoria = jokeRepository.findByCategory(category);
	        for (Joke joke : jokesConCategoria) {
	            joke.setCategory(null); // si se permite null en la columna
	            jokeRepository.save(joke);
	        }
	        categoryRepository.delete(category);
	    }
	}
}

