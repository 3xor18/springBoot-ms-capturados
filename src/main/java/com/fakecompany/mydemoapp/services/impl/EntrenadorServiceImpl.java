package com.fakecompany.mydemoapp.services.impl;

import com.fakecompany.mydemoapp.Repositories.EntrenadorRepository;
import com.fakecompany.mydemoapp.dtos.Request.EntrenadorDto;
import com.fakecompany.mydemoapp.dtos.Response.EntrenadorResponseDto;
import com.fakecompany.mydemoapp.entities.Entrenador;
import com.fakecompany.mydemoapp.services.EntrenadorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntrenadorServiceImpl implements EntrenadorService {

    private final EntrenadorRepository entrenadorRepository;

    public EntrenadorServiceImpl(EntrenadorRepository entrenadorRepository) {
        this.entrenadorRepository = entrenadorRepository;
    }

    @Override
    public ResponseEntity<?> guardar(EntrenadorDto dto) {
        //creamos y seteamos el entity
        Entrenador sinGuardar = new Entrenador();
        sinGuardar.setId(null);
        sinGuardar.setNombre(dto.getNombre());
        sinGuardar.setEmail(dto.getEmail());
        sinGuardar.setFechaNac(dto.getFechaNac());
        //Guardar en BD
        Entrenador guardado =entrenadorRepository.save(sinGuardar);

        //Parseamos al DTO de salida
        EntrenadorResponseDto dtoSalida= new EntrenadorResponseDto();

        dtoSalida.setNombre(guardado.getNombre());
        dtoSalida.setEmail(guardado.getEmail());
        dtoSalida.setFechaNac(guardado.getFechaNac());

        //Retornamos
        return ResponseEntity.status(HttpStatus.OK).body(dtoSalida);
    }

    @Override
    public ResponseEntity<?> buscarTodos() {
        //Buscamos en BD
        List<Entrenador> entrenadorDesdeBD = entrenadorRepository.findAll();

        //Retornamos
        return ResponseEntity.status(HttpStatus.OK).body(entrenadorDesdeBD);
    }

    @Override
    public ResponseEntity<?> buscarPorId(Long id) {
        Entrenador entrenadorDesdebd = entrenadorRepository.findById(id).orElse(null);
        //Retornamos
        return ResponseEntity.status(HttpStatus.OK).body(entrenadorDesdebd);
    }

    @Override
    public ResponseEntity<?> actualizar(Long id, EntrenadorDto dto) {
        Entrenador sinActualizar = new Entrenador();
        sinActualizar.setId(id);
        sinActualizar.setNombre(dto.getNombre());
        sinActualizar.setEmail(dto.getEmail());
        sinActualizar.setFechaNac(dto.getFechaNac());

        //Actualizamos en BD
        Entrenador actualizado = entrenadorRepository.save(sinActualizar);

        //Retornamos
        return ResponseEntity.status(HttpStatus.OK).body(actualizado);
    }

    @Override
    public ResponseEntity<?> borrar(Long id) {
        entrenadorRepository.deleteById(id);
        //Retornamos
        return ResponseEntity.status(HttpStatus.OK).body("Borrado!");
    }


}
