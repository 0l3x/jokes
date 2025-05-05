package olex.controllers;

import olex.models.entity.Type;
import olex.services.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/types")
public class TypeViewController {

    @Autowired
    private TypeService typeService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("titulo", "Listado de tipos");
        model.addAttribute("types", typeService.findAll());
        return "types/listar";
    }

    @GetMapping("/form")
    public String crear(Model model) {
        model.addAttribute("titulo", "Nuevo tipo");
        model.addAttribute("type", new Type());
        return "types/form";
    }

    @GetMapping("/form/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        model.addAttribute("titulo", "Editar tipo");
        model.addAttribute("type", typeService.findById(id));
        return "types/form";
    }

    @PostMapping("/form")
    public String guardar(@ModelAttribute Type type) {
        typeService.save(type);
        return "redirect:/types";
    }

    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable Integer id) {
        typeService.deleteById(id);
        return "redirect:/types";
    }
}
