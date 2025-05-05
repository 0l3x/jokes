package olex.repository;

import olex.models.entity.Type;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<Type, Long> {

	Optional<Type> findById(Integer typeId);

	void deleteById(Integer id);}



