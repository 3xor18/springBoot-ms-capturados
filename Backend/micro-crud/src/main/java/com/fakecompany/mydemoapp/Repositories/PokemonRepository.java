package com.fakecompany.mydemoapp.Repositories;

import com.fakecompany.mydemoapp.entities.Entrenador;
import com.fakecompany.mydemoapp.entities.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PokemonRepository extends JpaRepository<Pokemon,Long> {

    @Query("SELECT e FROM Pokemon e WHERE e.id = ?1")
    Optional<Pokemon> findByIdOptional(Long id);
}
