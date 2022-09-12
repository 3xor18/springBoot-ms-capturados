package com.fakecompany.mydemoapp.services.impl;

import com.fakecompany.mydemoapp.Repositories.PokemonRepository;
import com.fakecompany.mydemoapp.dtos.Request.PokemonDto;
import com.fakecompany.mydemoapp.entities.Entrenador;
import com.fakecompany.mydemoapp.entities.Pokemon;
import com.fakecompany.mydemoapp.exceptions.AppInternalServerErrorException;
import com.fakecompany.mydemoapp.exceptions.AppNotFoundException;
import com.fakecompany.mydemoapp.services.PokemonService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class PokemonServiceImpl implements PokemonService {

    private final PokemonRepository pokemonRepository;

    public PokemonServiceImpl(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }



    @Override
    public ResponseEntity<?> guardar(PokemonDto dto) throws AppInternalServerErrorException {
        try{
            Pokemon noGuardado = new Pokemon();
            noGuardado.setNombre(dto.getNombre());
            noGuardado.setFoto(dto.getFoto());
            Pokemon guardado = pokemonRepository.save(noGuardado);

            PokemonDto dtoSalida = new PokemonDto();
            dtoSalida.setFoto(guardado.getFoto());
            dtoSalida.setNombre(guardado.getNombre());

            return ResponseEntity.status(HttpStatus.OK).body(dtoSalida);
        }catch (Exception e){
            throw new AppInternalServerErrorException("Error al guardar Pokemon");
        }
    }

    @Override
    public ResponseEntity<?> buscarPorId(Long id) throws AppInternalServerErrorException, AppNotFoundException {
        try{
            Pokemon enBd= findById(id);
            return  ResponseEntity.status(HttpStatus.OK).body(enBd);
        }
        catch (AppNotFoundException e){
            throw new AppNotFoundException("Error al buscar un Pokemon:"+id);
        }
        catch (Exception e){
            throw new AppInternalServerErrorException("Error al buscar un Pokemon:"+id);
        }
    }

    @Override
    public ResponseEntity<?> buscarTodos() {
        List<Pokemon> pokemons = pokemonRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(pokemons);
    }

    @Override
    public ResponseEntity<?> editar(Long id, PokemonDto dto) throws AppNotFoundException, AppInternalServerErrorException {
        try{
            Pokemon enBd= findById(id);
            enBd.setFoto(dto.getFoto());
            enBd.setNombre(dto.getNombre());
            Pokemon editado=pokemonRepository.save(enBd);
            return ResponseEntity.status(HttpStatus.OK).body(editado);
        }
        catch (AppNotFoundException e){
            throw new AppNotFoundException("Error al buscar un Pokemon:"+id);
        }
        catch (Exception e){
            throw new AppInternalServerErrorException("Error al buscar un Pokemon:"+id);
        }
    }

    @Override
    public ResponseEntity<?> borrar(Long id) throws AppNotFoundException, AppInternalServerErrorException {
        try{
           findById(id);
           pokemonRepository.deleteById(id);
           return ResponseEntity.status(HttpStatus.OK).body("Pokemon borrado");
        }
        catch (AppNotFoundException e){
            throw new AppNotFoundException("No existe el pokemon :"+id);
        }
        catch (Exception e){
            throw new AppInternalServerErrorException("Error al eliminar un Pokemon:"+id);
        }
    }

    @Override
    public Pokemon findById(Long id) throws AppNotFoundException {
        log.info("findById: {}", id);
        return pokemonRepository.findByIdOptional(id)
                .orElseThrow(() -> new AppNotFoundException("No Encontre el pokemon con id: " + id));
    }
}
