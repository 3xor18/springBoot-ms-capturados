package com.fakecompany.mydemoapp.controllers;

import com.fakecompany.mydemoapp.dtos.Request.CapturadoDto;
import com.fakecompany.mydemoapp.dtos.Request.EntrenadorDto;
import com.fakecompany.mydemoapp.exceptions.AppInternalServerErrorException;
import com.fakecompany.mydemoapp.exceptions.AppNotFoundException;
import com.fakecompany.mydemoapp.services.CapturadoService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//Nos permite marcar la clase como un controlador rest
@RestController
//Es la url (endpoint) que va a exponer
@RequestMapping("capturado")
//para habilitar que sea consumido desde cualquier parte
@CrossOrigin("*")
//Para logear en consola
@Log4j2
public class CapturadosController {

    private final CapturadoService capturadoService;


    public CapturadosController(CapturadoService capturadoService) {
        this.capturadoService = capturadoService;
    }

    @GetMapping
    public ResponseEntity<?> buscarTodos() throws AppInternalServerErrorException {
        return capturadoService.buscarTodos();
    }

    @GetMapping("/campos")
    public ResponseEntity<?> buscarCampos() throws AppInternalServerErrorException {
        return capturadoService.buscarCampos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) throws AppNotFoundException, AppInternalServerErrorException {
        return capturadoService.buscarPorId(id);
    }

    @GetMapping("/{id1}/{id2}")
    public ResponseEntity<?> crearVs(@PathVariable Long id1,@PathVariable Long id2) throws AppNotFoundException, AppInternalServerErrorException {
        return capturadoService.crearDuelo(id1,id2);
    }

    @PostMapping
    public ResponseEntity<?> guardar( @RequestBody CapturadoDto dto) throws AppInternalServerErrorException, AppNotFoundException {
        return capturadoService.guardar(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody CapturadoDto dto) throws AppInternalServerErrorException, AppNotFoundException {
        return capturadoService.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id) throws AppInternalServerErrorException, AppNotFoundException {
        return capturadoService.borrar(id);
    }
}
