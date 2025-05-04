package olex.services;

import olex.models.entity.Type;
import olex.repository.TypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeService {

    @Autowired
    private TypeRepository typeRepository;

    public List<Type> findAll() {
        return typeRepository.findAll();
    }

    public Type findById(Long id) {
        return typeRepository.findById(id).orElseThrow();
    }

	public Type findById(Integer typeId) {
		return typeRepository.findById(typeId).orElseThrow();
	}
		
}
