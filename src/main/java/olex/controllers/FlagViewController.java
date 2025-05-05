package olex.controllers;

import olex.models.entity.Flag;
import olex.services.FlagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/flags")
public class FlagViewController {

    @Autowired
    private FlagService flagService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("titulo", "Listado de flags");
        model.addAttribute("flags", flagService.findAll());
        return "flags/listar";
    }

    @GetMapping("/form")
    public String crear(Model model) {
        model.addAttribute("titulo", "Crear flag");
        model.addAttribute("flag", new Flag());
        return "flags/form";
    }

    @PostMapping("/form")
    public String guardar(@ModelAttribute Flag flag) {
        flagService.save(flag);
        return "redirect:/flags";
    }
    
    @GetMapping("/form/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        Flag flag = flagService.findById(id);
        if (flag != null) {
            model.addAttribute("titulo", "Editar flag");
            model.addAttribute("flag", flag);
            return "flags/form";
        }
        return "redirect:/flags";
    }
    
    @GetMapping("/delete/{id}")
	public String eliminar(@PathVariable Integer id) {
		flagService.deleteById(id);
		return "redirect:/flags";
	}


}
