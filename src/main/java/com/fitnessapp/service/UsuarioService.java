package com.fitnessapp.service;

import com.fitnessapp.dto.UsuarioRequestDTO;
import com.fitnessapp.dto.UsuarioResponseDTO;
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


    public UsuarioResponseDTO registrarUsuario(UsuarioRequestDTO dto) {

        System.out.println("Revisando al usuario...");

        Usuario nuevoUsuario = new Usuario();

        nuevoUsuario.setNombre(dto.nombre());
        nuevoUsuario.setApellidos(dto.apellidos());
        nuevoUsuario.setEdad(dto.edad());
        nuevoUsuario.setPesoKg(dto.pesoKg());
        nuevoUsuario.setAlturaCm(dto.alturaCm());
        nuevoUsuario.setGenero(dto.genero());
        nuevoUsuario.setNivelActividad(dto.nivelActividad());
        nuevoUsuario.setObjetivo(dto.objetivo());
        nuevoUsuario.setProblemasSalud(dto.problemasSalud());

        double caloriasMantenimiento = calcularCalorias(nuevoUsuario);
        double caloriasFinales = aplicarObjetivo(nuevoUsuario);
        nuevoUsuario.setCaloriasRecomendadas(caloriasFinales);

        System.out.println("Cálculos terminados. Mandando a la base de datos...");

        //Guardar al usuario en la base de datos
        Usuario usuarioGuardado = usuarioRepository.save(nuevoUsuario);

        //Metemos al usuario en la caja de salida y devolvemos solo los datos públicos
        return convertirADtio(usuarioGuardado);
    }

    public List<UsuarioResponseDTO> obtenerTodosLosUsuarios(){
        System.out.println("Obteniendo la lista de usuarios...");

        List<Usuario> usuariosDeBaseDeDatos = usuarioRepository.findAll();

        return usuariosDeBaseDeDatos.stream()
                .map(this::convertirADtio)
                .toList();
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

    public Usuario actualizarUsuario(Long id, Usuario datosNuevos){
        System.out.println("Buscando al usuario con ID: " + id + " para actualizar los datos");
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));

        usuarioExistente.setNombre(datosNuevos.getNombre());
        usuarioExistente.setEdad(datosNuevos.getEdad());
        usuarioExistente.setPesoKg(datosNuevos.getPesoKg());
        usuarioExistente.setAlturaCm(datosNuevos.getAlturaCm());
        usuarioExistente.setObjetivo(datosNuevos.getObjetivo());
        usuarioExistente.setApellidos(datosNuevos.getApellidos());
        usuarioExistente.setNivelActividad(datosNuevos.getNivelActividad());
        usuarioExistente.setProblemasSalud(datosNuevos.getProblemasSalud());

        double caloriasFinales = aplicarObjetivo(usuarioExistente);
        usuarioExistente.setCaloriasRecomendadas(caloriasFinales);

        return usuarioRepository.save(usuarioExistente);
    }

    public void borrarUsuario(Long id){
        System.out.println("Eliminar al usuario con ID: " + id);

        if(!usuarioRepository.existsById(id)){
            throw new RuntimeException("No se puede borrar. Usuario no encontrado con ID: " + id);
        }

        usuarioRepository.deleteById(id);
    }

    private UsuarioResponseDTO convertirADtio(Usuario u){
        return new UsuarioResponseDTO(
                u.getId(),
                u.getNombre(),
                u.getObjetivo().toString(),
                u.getCaloriasRecomendadas()
        );
    }
}
