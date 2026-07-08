package com.fitnessapp.dto;

import com.fitnessapp.model.Genero;
import com.fitnessapp.model.NivelActividad;
import com.fitnessapp.model.Objetivo;

public record UsuarioRequestDTO(
        String nombre,
        String apellidos,
        int edad,
        double pesoKg,
        double alturaCm,
        Genero genero,
        NivelActividad nivelActividad,
        Objetivo objetivo,
        String problemasSalud
) {
}
