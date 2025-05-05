package olex.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import olex.services.TypeService;
import olex.models.entity.Type;

@RestController
@RequestMapping("/api/types")
public class TypeRestController {

    @Autowired
    private TypeService service;

    @GetMapping
    public List<Type> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Type> findById(@PathVariable Integer id) {
        Type type = service.findById(id);
        return type != null ? ResponseEntity.ok(type) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Type create(@RequestBody Type type) {
        service.save(type);
        return type;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Type> update(@RequestBody Type updatedType, @PathVariable Integer id) {
        Type existing = service.findById(id);
        if (existing == null) return ResponseEntity.notFound().build();
        existing.setName(updatedType.getName());
        service.save(existing);
        return ResponseEntity.ok(existing);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

