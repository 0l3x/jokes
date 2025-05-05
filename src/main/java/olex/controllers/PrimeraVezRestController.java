package olex.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import olex.dto.PrimeraVezDTO;
import olex.models.entity.PrimeraVez;
import olex.repository.PrimeraVezRepository;
import olex.services.PrimeraVezService;

@RestController
@RequestMapping("/api/primera_vez")
public class PrimeraVezRestController {

    @Autowired
    private PrimeraVezService primeraVezService;
    
    @Autowired
    private PrimeraVezRepository primeraVezRepository;
    
    // get
    @GetMapping
	public ResponseEntity<?> listarPrimeraVez() {
		return ResponseEntity.ok(primeraVezService.findAll());
	}
    
    // get by id
    @GetMapping("/{id}")
	public ResponseEntity<?> buscarPrimeraVezPorId(@PathVariable Integer id) {
		PrimeraVez primeraVez = primeraVezService.findById(id);
		if (primeraVez != null) {
			return ResponseEntity.ok(primeraVez);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
    
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody PrimeraVezDTO dto) {
		try {
			PrimeraVez primeraVez = primeraVezService.crearPrimeraVezConTelefonos(dto);
			return ResponseEntity.status(HttpStatus.CREATED).body(primeraVez);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
    }
}
