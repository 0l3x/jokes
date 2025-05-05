package olex.controllers;

import olex.models.entity.PrimeraVez;
import olex.models.entity.Telefono;
import olex.repository.JokeRepository;
import olex.repository.PrimeraVezRepository;
import olex.services.PrimeraVezService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/primera_vez")
public class PrimeraVezViewController {

    @Autowired
    private PrimeraVezRepository primeraVezRepository;

    @Autowired
    private JokeRepository jokeRepository;
    
    @Autowired
    private PrimeraVezService primeraVezService;

    @GetMapping
    public String listar(Model model) {
        List<PrimeraVez> apariciones = primeraVezService.findAll();
        model.addAttribute("titulo", "Listado de apariciones en TV");
        model.addAttribute("lista", apariciones);
        return "primera_vez/listar";
    }
    
    // Muestra el formulario para crear una nueva aparición en TV con todos los datos disponibles
    @GetMapping("/form")
    public String crear(Model model) {
        PrimeraVez pv = new PrimeraVez();
        pv.setTelefonos(List.of(new Telefono())); // mínimo 1 teléfono

        model.addAttribute("titulo", "Nueva aparición en TV");
        model.addAttribute("primeraVez", pv);
        model.addAttribute("jokes", jokeRepository.findAll());
        return "primera_vez/form";
    }
    
    // Guarda la aparición en TV
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute PrimeraVez primeraVez, BindingResult result, Model model) {
        if (result.hasErrors() || primeraVez.getTelefonos().isEmpty()) {
            model.addAttribute("titulo", "Error en formulario");
            model.addAttribute("jokes", jokeRepository.findAll());
            return "primera_vez/form";
        }

        primeraVez.getTelefonos().forEach(t -> t.setPrimeraVez(primeraVez));

        primeraVezRepository.save(primeraVez);
        return "redirect:/primera_vez";
    }
    
   
}
