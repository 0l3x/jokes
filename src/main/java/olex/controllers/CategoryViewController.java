package olex.controllers;

import olex.models.entity.Category;
import olex.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categories")
public class CategoryViewController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("titulo", "Listado de categorías");
        model.addAttribute("categories", categoryService.findAll());
        return "categories/listar";
    }

    @GetMapping("/form")
    public String crear(Model model) {
        model.addAttribute("titulo", "Nueva categoría");
        model.addAttribute("category", new Category());
        return "categories/form";
    }

    @GetMapping("/form/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        model.addAttribute("titulo", "Editar categoría");
        model.addAttribute("category", categoryService.findById(id));
        return "categories/form";
    }

    @PostMapping("/form")
    public String guardar(@ModelAttribute Category category) {
        categoryService.save(category);
        return "redirect:/categories";
    }

    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable Integer id) {
        categoryService.delete(id);
        return "redirect:/categories";
    }
}
