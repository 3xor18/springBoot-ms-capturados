package com.fakecompany.mydemoapp.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

//Es para decirle es es una tabla de BASE DE DATOS
@Entity
// nombre de la tabla dentro de la bd
@Table(name = "entrenadores")
//Lombok: getters, setters, contructurs, equals
@Data
//crear un constructor por defecto
@NoArgsConstructor
public class Entrenador implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@NotEmpty(message = "es requerido")
    //@Size(min = 4, max = 12, message = "el tamaño tiene que estar entre 4 y 12")
    @Column(name="nombre")
    private String nombre;


    @Temporal(TemporalType.DATE)
    private Date fechaNac;


    private String email;



}
