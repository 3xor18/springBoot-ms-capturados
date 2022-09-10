package com.fakecompany.mydemoapp.Repositories;

import com.fakecompany.mydemoapp.entities.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonRepository extends JpaRepository<Pokemon,Long> {
}
