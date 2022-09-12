package com.fakecompany.mydemoapp.services;

import com.fakecompany.mydemoapp.dtos.Request.CapturadoDto;
import com.fakecompany.mydemoapp.dtos.Request.EntrenadorDto;
import com.fakecompany.mydemoapp.exceptions.AppInternalServerErrorException;
import com.fakecompany.mydemoapp.exceptions.AppNotFoundException;
import org.springframework.http.ResponseEntity;

public interface CapturadoService {

    public ResponseEntity<?> guardar(CapturadoDto dto) throws AppInternalServerErrorException, AppNotFoundException;

    public ResponseEntity<?> buscarTodos() throws AppInternalServerErrorException;

    public ResponseEntity<?> buscarPorId(Long id) throws AppNotFoundException, AppInternalServerErrorException;

    public ResponseEntity<?> actualizar(Long id, CapturadoDto dto) throws AppNotFoundException, AppInternalServerErrorException;

    public ResponseEntity<?> borrar(Long id) throws AppNotFoundException, AppInternalServerErrorException;


    public ResponseEntity<?> buscarCampos();

    public ResponseEntity<?> crearDuelo(Long idEntrenador1,Long idEntrenador2) throws AppInternalServerErrorException;
}
