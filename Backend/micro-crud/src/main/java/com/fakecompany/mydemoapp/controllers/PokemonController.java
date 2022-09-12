package com.fakecompany.mydemoapp.controllers;

import com.fakecompany.mydemoapp.dtos.Request.EntrenadorDto;
import com.fakecompany.mydemoapp.dtos.Request.PokemonDto;
import com.fakecompany.mydemoapp.exceptions.AppInternalServerErrorException;
import com.fakecompany.mydemoapp.exceptions.AppNotFoundException;
import com.fakecompany.mydemoapp.services.PokemonService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

//Nos permite marcar la clase como un controlador rest
@RestController
//Es la url (endpoint) que va a exponer
@RequestMapping("pokemon")
//para habilitar que sea consumido desde cualquier parte
@CrossOrigin("*")
//Para logear en consola
@Log4j2
public class PokemonController {

    private final PokemonService pokemonService;

    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping
    public ResponseEntity<?> buscarTodos() throws AppInternalServerErrorException {
        return pokemonService.buscarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) throws AppNotFoundException, AppInternalServerErrorException {
        return pokemonService.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody PokemonDto dto) throws AppInternalServerErrorException {
            return pokemonService.guardar(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody PokemonDto dto) throws AppInternalServerErrorException, AppNotFoundException {
        return pokemonService.editar(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id) throws AppInternalServerErrorException, AppNotFoundException {
        return pokemonService.borrar(id);
    }

}
