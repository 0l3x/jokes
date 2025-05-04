package olex.controllers;

import olex.models.entity.Flag;
import olex.repository.FlagRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/flags")
public class FlagViewController {

    @Autowired
    private FlagRepository flagRepository;

    @GetMapping
    public String listFlags(Model model) {
        model.addAttribute("flags", flagRepository.findAll());
        return "flags/list";
    }

    @GetMapping("/new")
    public String newFlag(Model model) {
        model.addAttribute("flag", new Flag());
        return "flags/form";
    }

    @GetMapping("/edit/{id}")
    public String editFlag(@PathVariable Integer id, Model model) {
        model.addAttribute("flag", flagRepository.findById(id).orElseThrow());
        return "flags/form";
    }

    @PostMapping("/save")
    public String saveFlag(@ModelAttribute Flag flag) {
        flagRepository.save(flag);
        return "redirect:/flags";
    }

    @GetMapping("/delete/{id}")
    public String deleteFlag(@PathVariable Integer id) {
        flagRepository.deleteById(id);
        return "redirect:/flags";
    }
}

