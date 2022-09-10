package com.fakecompany.mydemoapp.dtos.Request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

//Es casi lo mismo que el @Entity pero decimos q no es una tabla (getters,seterrs....)
@Data
//Declaramos un constructor por defecto
@NoArgsConstructor
public class EntrenadorDto implements Serializable {

    @JsonProperty(value = "nombre", index = 1)
    private String nombre;

    @JsonProperty(value = "fechaNac", index = 2)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date fechaNac;

    @JsonProperty(value = "email", index = 3)
    private String email;
}
