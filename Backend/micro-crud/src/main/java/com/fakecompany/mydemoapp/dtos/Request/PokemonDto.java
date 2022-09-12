package com.fakecompany.mydemoapp.dtos.Request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

//Es casi lo mismo que el @Entity pero decimos q no es una tabla
@Data
//Declaramos un constructor por defecto
@NoArgsConstructor
public class PokemonDto implements Serializable {

    @NotEmpty(message = "Requerido")
    //Es para decirle que nombre va a tener el valor de entrada/salida
    @JsonProperty(value = "nombre", index = 1)
    // nombre dentro de nuestra app
    private String nombre;


    @JsonProperty(value = "foto", index = 2)
    private String foto;
}
