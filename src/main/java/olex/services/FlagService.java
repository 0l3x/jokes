package olex.services;

import olex.models.entity.Flag;
import olex.repository.FlagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlagService {
    @Autowired
    private FlagRepository flagRepository;

    public List<Flag> findAll() {
        return flagRepository.findAll();
    }

    public Flag findById(Integer id) {
        return flagRepository.findById(id).orElse(null);
    }

    public void save(Flag flag) {
        flagRepository.save(flag);
    }

    public void deleteById(Integer id) {
        flagRepository.deleteById(id);
    }
}
