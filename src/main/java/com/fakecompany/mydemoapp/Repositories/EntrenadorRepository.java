package com.fakecompany.mydemoapp.Repositories;

import com.fakecompany.mydemoapp.entities.Entrenador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntrenadorRepository  extends JpaRepository<Entrenador, Long> {
}
