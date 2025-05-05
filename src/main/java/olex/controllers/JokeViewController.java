package olex.controllers;

import olex.models.entity.Flag;
import olex.models.entity.Joke;
import olex.models.entity.PrimeraVez;
import olex.repository.JokeRepository;
import olex.repository.PrimeraVezRepository;
import olex.services.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/jokes")
public class JokeViewController {

    @Autowired
    private JokeService jokeService;

    @Autowired
    private FlagService flagService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TypeService typeService;
    
    @Autowired
    private LanguageService languageService;
    
    @Autowired
    private JokeFlagService jokeFlagService;
    
    @Autowired
    private JokeRepository jokeRepository;
    
    @Autowired
    private PrimeraVezRepository primeraVezRepository;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("titulo", "Listado de chistes");
        model.addAttribute("jokes", jokeService.findAll());
        return "jokes/listar";
    }

    @GetMapping("/form")
    public String crear(Model model) {
        model.addAttribute("titulo", "Crear chiste");
        model.addAttribute("joke", new Joke());
        model.addAttribute("flags", flagService.findAll());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("types", typeService.findAll());
        model.addAttribute("languages", languageService.findAll());
        return "jokes/form";
    }

    @GetMapping("/form/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Joke joke = jokeService.findById(id);
        model.addAttribute("titulo", "Editar chiste");
        model.addAttribute("joke", joke);
        model.addAttribute("flags", flagService.findAll());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("types", typeService.findAll());
        model.addAttribute("languages", languageService.findAll());
        return "jokes/form";
    }

    @PostMapping("/form")
    public String guardar(@ModelAttribute Joke joke,
                          @RequestParam("category.id") Integer categoryId,
                          @RequestParam("type.id") Integer typeId,
                          @RequestParam("language.id") Integer languageId) {

        joke.setCategory(categoryService.findById(categoryId));
        joke.setType(typeService.findById(typeId));
        joke.setLanguage(languageService.findById(languageId)); 

        jokeService.save(joke);
        return "redirect:/jokes";
    }


    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable Long id) {
    	Joke joke = jokeRepository.findById(id).orElse(null);
        if (joke != null) {
            // Rompe las relaciones con PrimeraVez antes de eliminarse
            List<PrimeraVez> apariciones = primeraVezRepository.findByJoke(joke);
            apariciones.forEach(pv -> {
                pv.setJoke(null); // Rompe la relación
                primeraVezRepository.save(pv);
            });

            jokeRepository.delete(joke); // Ahora sí se puede eliminar
        }
        return "redirect:/jokes";
    }
    
    @GetMapping("/{id}/flags")
    public String gestionarFlags(@PathVariable Integer id, Model model) {
        Joke joke = jokeService.findById(id);
        List<Flag> allFlags = flagService.findAll();
        List<Flag> assigned = jokeFlagService.findFlagsByJokeId(id); 
        List<Flag> available = allFlags.stream()
            .filter(f -> !assigned.contains(f))
            .toList();

        model.addAttribute("joke", joke);
        model.addAttribute("assignedFlags", assigned);
        model.addAttribute("availableFlags", available);
        return "jokes/flags";
    }

    @PostMapping("/{id}/flags/add")
    public String asignarFlag(@PathVariable Integer id, @RequestParam Integer flagId) {
        jokeFlagService.addFlagToJoke(id, flagId); 
        return "redirect:/jokes/" + id + "/flags";
    }

    @GetMapping("/{id}/flags/remove")
    public String eliminarFlag(@PathVariable Integer id, @RequestParam Integer flagId) {
        jokeFlagService.removeFlagFromJoke(id, flagId); 
        return "redirect:/jokes/" + id + "/flags";
    }
    
    @GetMapping("/search")
    public String buscarPorTexto(@RequestParam("text") String text, Model model) {
        List<Joke> jokes = jokeRepository.findByText1ContainingIgnoreCase(text);
        model.addAttribute("titulo", "Resultados de búsqueda");
        model.addAttribute("jokes", jokes);
        return "jokes/listar";
    }
    
    @GetMapping("/sin-primera-vez")
    public String listarSinPrimeraVez(Model model) {
        List<Joke> jokes = jokeRepository.findAllWithoutPrimeraVez();
        model.addAttribute("titulo", "Chistes sin aparición en TV");
        model.addAttribute("jokes", jokes);
        return "jokes/listar"; 
    }



}
