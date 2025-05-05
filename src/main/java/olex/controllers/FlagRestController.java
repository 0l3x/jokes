package olex.controllers;

import olex.dto.FlagDTO;
import olex.models.entity.Flag;
import olex.repository.FlagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flags")
public class FlagRestController {

    @Autowired
    private FlagRepository flagRepository;

    @GetMapping
    public List<Flag> findAll() {
        return flagRepository.findAll();
    }

    @GetMapping("/{id}")
    public Flag findById(@PathVariable Integer id) {
        return flagRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Flag create(@RequestBody FlagDTO flagDTO) {
        Flag flag = new Flag();
        flag.setName(flagDTO.getName());
        return flagRepository.save(flag);
    }

    @PutMapping("/{id}")
    public Flag update(@PathVariable Integer id, @RequestBody FlagDTO flagDTO) {
        Flag flag = flagRepository.findById(id).orElseThrow();
        flag.setName(flagDTO.getName());
        return flagRepository.save(flag);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        flagRepository.deleteById(id);
    }
}
