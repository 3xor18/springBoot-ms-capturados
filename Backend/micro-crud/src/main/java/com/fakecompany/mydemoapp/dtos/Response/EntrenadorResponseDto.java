package com.fakecompany.mydemoapp.dtos.Response;

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
public class EntrenadorResponseDto implements Serializable {

    @JsonProperty(value = "id", index = 1)
    private Long id;

    @JsonProperty(value = "nombre", index = 4)
    private String nombre;

    @JsonProperty(value = "Date", index = 3)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date fechaNac;

    @JsonProperty(value = "email", index = 2)
    private String email;
}
