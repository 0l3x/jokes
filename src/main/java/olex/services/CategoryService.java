package olex.services;

import olex.models.entity.Category;
import olex.repository.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

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

	public void delete(Long id) {
		categoryRepository.deleteById(id);
	}

	public void delete(Integer id) {
		categoryRepository.deleteById(id);
	}
}

