package olex.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import olex.models.entity.Language;
import olex.services.LanguageService;

@Controller
@RequestMapping("/languages")
public class LanguageViewController {

    @Autowired
    private LanguageService languageService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("titulo", "Listado de idiomas");
        model.addAttribute("languages", languageService.findAll());
        return "languages/listar";
    }

    @GetMapping("/form")
    public String crear(Model model) {
        model.addAttribute("titulo", "Nuevo idioma");
        model.addAttribute("language", new Language());
        return "languages/form";
    }

    @GetMapping("/form/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        model.addAttribute("titulo", "Editar idioma");
        model.addAttribute("language", languageService.findById(id));
        return "languages/form";
    }

    @PostMapping("/form")
    public String guardar(@ModelAttribute Language language) {
        languageService.save(language);
        return "redirect:/languages";
    }

    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable Integer id) {
        languageService.deleteById(id);
        return "redirect:/languages";
    }
}

