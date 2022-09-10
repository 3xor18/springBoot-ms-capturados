package com.fakecompany.mydemoapp.services;

import com.fakecompany.mydemoapp.dtos.Request.EntrenadorDto;
import org.springframework.http.ResponseEntity;

public interface EntrenadorService {

    public ResponseEntity<?> guardar(EntrenadorDto dto);

    public ResponseEntity<?> buscarTodos();

    public ResponseEntity<?> buscarPorId(Long id);

    public ResponseEntity<?> actualizar(Long id, EntrenadorDto dto);

    public ResponseEntity<?> borrar(Long id);

}
