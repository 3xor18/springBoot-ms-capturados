package com.fakecompany.mydemoapp.controllers;

import com.fakecompany.mydemoapp.dtos.Request.EntrenadorDto;
import com.fakecompany.mydemoapp.services.EntrenadorService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//Nos permite marcar la clase como un controlador rest
@RestController
//Es la url (endpoint) que va a exponer
@RequestMapping("entrenador")
//para habilitar que sea consumido desde cualquier parte
@CrossOrigin("*")
//Para logear en consola
@Log4j2
public class EntrenadorController {

    private final EntrenadorService entrenadorService;

    public EntrenadorController(EntrenadorService entrenadorService) {
        this.entrenadorService = entrenadorService;
    }


    @GetMapping
    public ResponseEntity<?> buscarTodos() {
        return entrenadorService.buscarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        return entrenadorService.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody EntrenadorDto dto) {
        return entrenadorService.guardar(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody EntrenadorDto dto) {
        return entrenadorService.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id){
        return entrenadorService.borrar(id);
    }
}
