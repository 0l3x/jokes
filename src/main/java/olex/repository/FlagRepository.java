package olex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import olex.models.entity.Flag;

@Repository
public interface FlagRepository extends JpaRepository<Flag, Integer> {
}
