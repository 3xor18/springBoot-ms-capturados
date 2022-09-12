package com.fakecompany.mydemoapp.services;

import com.fakecompany.mydemoapp.dtos.Request.EntrenadorDto;
import com.fakecompany.mydemoapp.entities.Entrenador;
import com.fakecompany.mydemoapp.exceptions.AppInternalServerErrorException;
import com.fakecompany.mydemoapp.exceptions.AppNotFoundException;
import org.springframework.http.ResponseEntity;

public interface EntrenadorService {

    public ResponseEntity<?> guardar(EntrenadorDto dto);

    public ResponseEntity<?> buscarTodos() throws AppInternalServerErrorException;

    public ResponseEntity<?> buscarPorId(Long id) throws AppNotFoundException;

    public ResponseEntity<?> actualizar(Long id, EntrenadorDto dto) throws AppNotFoundException, AppInternalServerErrorException;

    public ResponseEntity<?> borrar(Long id) throws AppNotFoundException, AppInternalServerErrorException;

    public Entrenador findById(Long id) throws AppNotFoundException;

}
