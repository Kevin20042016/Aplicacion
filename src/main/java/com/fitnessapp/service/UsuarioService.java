package com.fitnessapp.service;

import com.fitnessapp.model.Genero;
import com.fitnessapp.model.Usuario;
import com.fitnessapp.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;


    public UsuarioService(UsuarioRepository usuarioRepository) {

    this.usuarioRepository = usuarioRepository;
    }


    public Usuario registrarUsuario(Usuario nuevoUsuario) {

        System.out.println("El Chef está revisando al usuario: " + nuevoUsuario.getNombre());

        double caloriasMantenimiento = calcularCalorias(nuevoUsuario);

        double caloriasFinales = aplicarObjetivo(nuevoUsuario);
        nuevoUsuario.setCaloriasRecomendadas(caloriasFinales);

        System.out.println("Cálculos terminados. Mandando a la base de datos...");

        return usuarioRepository.save(nuevoUsuario);
    }

    public List<Usuario> obtenerTodosLosUsuarios(){
        System.out.println("Obteniendo la lista de usuarios...");
        return usuarioRepository.findAll();
    }

    private double calcularCalorias(Usuario u) {
        double metabolismoBasal = 0;

        if (u.getGenero() == Genero.MASCULINO){
            metabolismoBasal = (10 * u.getPesoKg()) + (6.25 * u.getAlturaCm()) - (5 * u.getEdad()) + 5;
        }
        else if (u.getGenero() == Genero.FEMENINO) {
            metabolismoBasal = (10 * u.getPesoKg()) + (6.25 * u.getAlturaCm()) - (5 * u.getEdad()) - 161;
        }
        else {
            throw new IllegalArgumentException("Error de cálculo: El género debe ser (Masculino) o (Femenino).");
        }


        double caloriasMantenimiento = 0;

        switch (u.getNivelActividad()) {
            case SEDENTARIO:
                caloriasMantenimiento = metabolismoBasal * 1.2;
                break;
            case LIGERO:
                caloriasMantenimiento = metabolismoBasal * 1.375;
                break;
            case MODERADO:
                caloriasMantenimiento = metabolismoBasal * 1.55;
                break;
            case INTENSO:
                caloriasMantenimiento = metabolismoBasal * 1.725;
                break;
            case ATLETA:
                caloriasMantenimiento = metabolismoBasal * 1.9;
                break;
        }
        return caloriasMantenimiento;
    }

    private double aplicarObjetivo(Usuario u) {

        double caloriasBase = calcularCalorias(u);

        return switch (u.getObjetivo()) {
            case BAJAR_PESO -> caloriasBase - 500.0;
            case AUMENTAR_PESO -> caloriasBase + 500;
            case MANTENER -> caloriasBase;
            case MEJORAR_RENDIMIENTO -> caloriasBase + 250;
            default -> throw new IllegalArgumentException("Objetivo no válido");
        };
    }
}
