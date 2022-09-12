package com.fakecompany.mydemoapp.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


//Es para decirle es es una tabla de BASE DE DATOS
@Entity
// nombre de la tabla dentro de la bd
@Table(name = "pokemons")
//Lombok: getters, setters, contructurs, equals
@Data
//crear un constructor por defecto
@NoArgsConstructor
public class Pokemon implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String foto;

}
