package com.fakecompany.mydemoapp.services.impl;

import com.fakecompany.mydemoapp.Repositories.EntrenadorRepository;
import com.fakecompany.mydemoapp.dtos.Request.EntrenadorDto;
import com.fakecompany.mydemoapp.dtos.Response.EntrenadorResponseDto;
import com.fakecompany.mydemoapp.entities.Entrenador;
import com.fakecompany.mydemoapp.exceptions.AppInternalServerErrorException;
import com.fakecompany.mydemoapp.exceptions.AppNotFoundException;
import com.fakecompany.mydemoapp.services.EntrenadorService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class EntrenadorServiceImpl implements EntrenadorService {

    private final EntrenadorRepository entrenadorRepository;

    public EntrenadorServiceImpl(EntrenadorRepository entrenadorRepository) {
        this.entrenadorRepository = entrenadorRepository;
    }

    @Override
    public Entrenador findById(Long id) throws AppNotFoundException {
        log.info("findById: {}", id);
        return entrenadorRepository.findByIdOptional(id)
                .orElseThrow(() -> new AppNotFoundException("No Encontre el entrenador con id: " + id));
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
    public ResponseEntity<?> buscarTodos() throws AppInternalServerErrorException {
        try{
            //Buscamos en BD
            List<Entrenador> entrenadorDesdeBD = entrenadorRepository.findAll();

            //Retornamos
            return ResponseEntity.status(HttpStatus.OK).body(entrenadorDesdeBD);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new AppInternalServerErrorException("Error al buscar todos los Entrenadores");
        }
    }

    @Override
    public ResponseEntity<?> buscarPorId(Long id) throws AppNotFoundException {
        Entrenador entrenadorDesdebd = findById(id);
        //Retornamos
        return ResponseEntity.status(HttpStatus.OK).body(entrenadorDesdebd);
    }

    @Override
    public ResponseEntity<?> actualizar(Long id, EntrenadorDto dto) throws AppNotFoundException, AppInternalServerErrorException {
        try{
            findById(id);

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
        catch (AppNotFoundException e){
            e.printStackTrace();
            throw new AppNotFoundException(e.getMessage());
        }
        catch (Exception e){
            e.printStackTrace();
            throw new AppInternalServerErrorException("Error al borrar el entrenador");
        }
    }

    @Override
    public ResponseEntity<?> borrar(Long id) throws AppNotFoundException, AppInternalServerErrorException {
        try{
            findById(id);
            entrenadorRepository.deleteById(id);
            //Retornamos
            return ResponseEntity.status(HttpStatus.OK).body("Borrado!");
        }
        catch (AppNotFoundException e){
            e.printStackTrace();
            throw new AppNotFoundException(e.getMessage());
        }
        catch (Exception e){
            e.printStackTrace();
            throw new AppInternalServerErrorException("Error al borrar el entrenador");
        }
    }


}
