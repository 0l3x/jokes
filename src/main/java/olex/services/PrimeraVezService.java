package olex.services;

import olex.dto.PrimeraVezDTO;
import olex.models.entity.PrimeraVez;
import olex.models.entity.Telefono;
import olex.models.entity.Joke;
import olex.repository.JokeRepository;
import olex.repository.PrimeraVezRepository;
import olex.repository.TelefonoRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PrimeraVezService {

    @Autowired
    private JokeRepository jokeRepository;

    @Autowired
    private PrimeraVezRepository primeraVezRepository;

    @Autowired
    private TelefonoRepository telefonoRepository;
    
    public List<PrimeraVez> findAll() {
        return primeraVezRepository.findAll();
    }

    @Transactional
    public PrimeraVez crearPrimeraVezConTelefonos(PrimeraVezDTO dto) {
        if (dto.getTelefonos() == null || dto.getTelefonos().isEmpty()) {
            throw new IllegalArgumentException("Debe incluir al menos un teléfono.");
        }

        Joke joke = jokeRepository.findById(dto.getIdJoke())
                .orElseThrow(() -> new IllegalArgumentException("No existe el chiste con ID " + dto.getIdJoke()));

        PrimeraVez primeraVez = new PrimeraVez();
        primeraVez.setPrograma(dto.getPrograma());
        primeraVez.setFechaEmision(dto.getFechaEmision());
        primeraVez.setJoke(joke);

        primeraVez = primeraVezRepository.save(primeraVez);

        for (String numero : dto.getTelefonos()) {
            Telefono telefono = new Telefono();
            telefono.setNumero(numero);
            telefono.setPrimeraVez(primeraVez);
            telefonoRepository.save(telefono);
        }

        return primeraVez;
    }

	public PrimeraVez findById(Integer id) {
		return primeraVezRepository.findById(id).orElse(null);
	}
	
	
}
