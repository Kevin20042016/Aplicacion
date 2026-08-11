package com.fitnessapp.dto;

public record UsuarioResponseDTO(
        Long id,
        String nombre,
        String objetivo,
        double caloriasRecomendadas
) {
}
