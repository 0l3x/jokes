package olex.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import olex.models.entity.Flag;
import olex.repository.FlagRepository;

import java.util.List;

@RestController
@RequestMapping("/api/flags")
public class FlagController {

    @Autowired
    private FlagRepository flagRepository;

    @GetMapping
    public List<Flag> getAllFlags() {
        return flagRepository.findAll();
    }

    @GetMapping("/{id}")
    public Flag getFlagById(@PathVariable Integer id) {
        return flagRepository.findById(id).orElseThrow();
    }

    @PostMapping
    public Flag createFlag(@RequestBody Flag flag) {
        return flagRepository.save(flag);
    }

    @PutMapping("/{id}")
    public Flag updateFlag(@PathVariable Integer id, @RequestBody Flag flagDetails) {
        Flag flag = flagRepository.findById(id).orElseThrow();
        flag.setFlag(flagDetails.getFlag());
        return flagRepository.save(flag);
    }

    @DeleteMapping("/{id}")
    public void deleteFlag(@PathVariable Integer id) {
        flagRepository.deleteById(id);
    }
}

