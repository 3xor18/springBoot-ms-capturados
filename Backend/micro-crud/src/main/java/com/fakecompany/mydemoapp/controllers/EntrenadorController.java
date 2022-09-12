package com.fakecompany.mydemoapp.controllers;

import com.fakecompany.mydemoapp.dtos.Request.EntrenadorDto;
import com.fakecompany.mydemoapp.exceptions.AppInternalServerErrorException;
import com.fakecompany.mydemoapp.exceptions.AppNotFoundException;
import com.fakecompany.mydemoapp.services.EntrenadorService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public ResponseEntity<?> buscarTodos() throws AppInternalServerErrorException {
        return entrenadorService.buscarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) throws AppNotFoundException {
        return entrenadorService.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<?> guardar( @RequestBody EntrenadorDto dto) {
            return entrenadorService.guardar(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody EntrenadorDto dto) throws AppInternalServerErrorException, AppNotFoundException {
        return entrenadorService.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id) throws AppInternalServerErrorException, AppNotFoundException {
        return entrenadorService.borrar(id);
    }

    private ResponseEntity<?> hasError(BindingResult result){
        log.info("Ha Ocurrido un error en uno de los campos del Request");
        Map<String, Object> response = new HashMap<>();

        List<String> errors = result.getFieldErrors()
                .stream()
                .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                .collect(Collectors.toList());

        response.put("errors", errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
