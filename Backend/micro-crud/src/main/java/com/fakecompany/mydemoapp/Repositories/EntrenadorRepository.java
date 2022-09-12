package com.fakecompany.mydemoapp.Repositories;

import com.fakecompany.mydemoapp.entities.Entrenador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EntrenadorRepository  extends JpaRepository<Entrenador, Long> {

    @Query("SELECT e FROM Entrenador e WHERE e.id = ?1")
    Optional<Entrenador> findByIdOptional(Long id);
}
