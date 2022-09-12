package com.fakecompany.mydemoapp.dtos.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

//Es casi lo mismo que el @Entity pero decimos q no es una tabla (getters,seterrs....)
@Data
//Declaramos un constructor por defecto
@NoArgsConstructor
public class CapturadoDto {

    @JsonProperty(value = "idPokemon", index = 1)
    private Long idPokemon;

    @JsonProperty(value = "idEntrenador", index = 2)
    private Long idEntrenador;
}
