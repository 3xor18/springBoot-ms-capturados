package com.fakecompany.mydemoapp.Repositories;

import com.fakecompany.mydemoapp.entities.Capturado;
import com.fakecompany.mydemoapp.entities.Entrenador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CapturadoRepositoy extends JpaRepository<Capturado,Long> {

    @Query("SELECT e FROM Capturado e WHERE e.id = ?1")
    Optional<Capturado> findByIdOptional(Long id);


    @Query("SELECT e FROM Capturado e WHERE e.entrenador.id=?1 AND e.pokemon.id=?2")
    Capturado buscarSiExisteEnEntrenador(Long idEntrenador,Long idPokemon);
}
