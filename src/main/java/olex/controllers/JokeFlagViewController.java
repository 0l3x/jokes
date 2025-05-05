package olex.controllers;


import olex.models.entity.*;
import olex.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/jokes/flags")
public class JokeFlagViewController {

    @Autowired private JokeRepository jokeRepository;
    @Autowired private FlagRepository flagRepository;
    @Autowired private JokeFlagRepository jokeFlagRepository;

    @GetMapping("/{id}")
    public String showFlags(@PathVariable Integer id, Model model) {
        Joke joke = jokeRepository.findById(id).orElseThrow();
        model.addAttribute("joke", joke);

        // muestra flags ya asignados al chiste
        var assigned = jokeFlagRepository.findAll().stream()
                .filter(jf -> jf.getJoke().getId().equals(id))
                .map(JokeFlag::getFlag)
                .toList();

        // Flags disponibles
        var available = flagRepository.findAll().stream()
                .filter(f -> !assigned.contains(f))
                .toList();

        model.addAttribute("assignedFlags", assigned);
        model.addAttribute("availableFlags", available);
        return "jokes/flags";
    }

    @PostMapping("/add")
    public String addFlag(@RequestParam Integer jokeId, @RequestParam Integer flagId) {
        Joke joke = jokeRepository.findById(jokeId).orElseThrow();
        Flag flag = flagRepository.findById(flagId).orElseThrow();

        JokeFlag jokeFlag = new JokeFlag();
        jokeFlag.setJoke(joke);
        jokeFlag.setFlag(flag);
        jokeFlagRepository.save(jokeFlag);

        return "redirect:/jokes/flags/" + jokeId;
    }

    @GetMapping("/remove")
    public String removeFlag(@RequestParam Integer jokeId, @RequestParam Integer flagId) {
        jokeFlagRepository.deleteById(new JokeFlagId(jokeId, flagId));
        return "redirect:/jokes/flags/" + jokeId;
    }
}

