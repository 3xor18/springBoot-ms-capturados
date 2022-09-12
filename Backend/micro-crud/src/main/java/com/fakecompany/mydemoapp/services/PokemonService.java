package com.fakecompany.mydemoapp.services;

import com.fakecompany.mydemoapp.dtos.Request.PokemonDto;
import com.fakecompany.mydemoapp.entities.Pokemon;
import com.fakecompany.mydemoapp.exceptions.AppInternalServerErrorException;
import com.fakecompany.mydemoapp.exceptions.AppNotFoundException;
import org.springframework.http.ResponseEntity;

public interface PokemonService {

    public ResponseEntity<?> guardar(PokemonDto dto) throws AppInternalServerErrorException;

    public ResponseEntity<?> buscarPorId(Long id) throws AppInternalServerErrorException, AppNotFoundException;

    public ResponseEntity<?> buscarTodos();

    public ResponseEntity<?> editar(Long id,PokemonDto dto) throws AppNotFoundException, AppInternalServerErrorException;

    public ResponseEntity<?> borrar(Long id) throws AppNotFoundException, AppInternalServerErrorException;

    public Pokemon findById(Long id) throws AppNotFoundException;
}
