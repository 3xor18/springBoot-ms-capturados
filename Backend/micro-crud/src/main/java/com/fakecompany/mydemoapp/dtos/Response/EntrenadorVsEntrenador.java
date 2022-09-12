package com.fakecompany.mydemoapp.dtos.Response;

import com.fakecompany.mydemoapp.entities.Entrenador;
import lombok.Data;

@Data
public class EntrenadorVsEntrenador {

    private Entrenador entrenador1;
    private Entrenador entrenador2;
    private CamposResponse campoDeBatalla;
}
