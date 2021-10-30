package com.example.mutante.repositories;

import com.example.mutante.entidades.Mutant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface MutantRepositories extends JpaRepository<Mutant, Long> {
}
