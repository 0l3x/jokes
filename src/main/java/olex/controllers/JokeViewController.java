package olex.controllers;

import olex.models.entity.Joke;
import olex.services.FlagService;
import olex.services.JokeService;
import olex.services.CategoryService;
import olex.services.TypeService;

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
        return "jokes/form";
    }

    @PostMapping("/form")
    public String guardar(@ModelAttribute Joke joke,
                          @RequestParam("category.id") Integer categoryId,
                          @RequestParam("type.id") Integer typeId,
                          @RequestParam("language.id") Integer languageId) {

        joke.setCategory(categoryService.findById(categoryId));
        joke.setType(typeService.findById(typeId));
//        joke.setLanguage(languageService.findById(languageId)); // si lo necesitas

        jokeService.save(joke);
        return "redirect:/jokes";
    }


    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable Long id) {
        jokeService.deleteById(id);
        return "redirect:/jokes";
    }
}
