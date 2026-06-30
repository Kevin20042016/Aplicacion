package com.fitnessapp.controller;

import com.fitnessapp.dto.UsuarioRequestDTO;
import com.fitnessapp.dto.UsuarioResponseDTO;
import com.fitnessapp.model.Usuario;
import com.fitnessapp.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 1. Convertimos esta clase en el Recepcionista que devuelve JSON
@RestController
// 2. Definimos la ruta de la calle donde trabaja este camarero
@RequestMapping("/api/usuarios")
public class UsuarioController {

    // 3. El Camarero necesita al Chef (Service) para trabajar
    private final UsuarioService usuarioService;

    // Inyección de Dependencias (Spring nos pasa el Chef automáticamente)
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // --- ENDPOINT 1: Prueba de conexión (Verbo GET) ---
    // Si entras en tu navegador a http://localhost:8080/api/usuarios/ping
    @GetMapping("/ping")
    public String ping() {
        return "¡El recepcionista de fitnessapp está despierto y listo para recibir usuarios!";
    }

    // --- ENDPOINT 2: Crear un usuario (Verbo POST) ---
    // @RequestBody agarra el texto de internet y lo convierte mágicamente en un objeto Usuario
    @PostMapping
    public UsuarioResponseDTO crearUsuario(@RequestBody UsuarioRequestDTO nuevoUsuario) {
        System.out.println("Petición HTTP recibida para registrar a " + nuevoUsuario.nombre());
        return usuarioService.registrarUsuario(nuevoUsuario);
    }

    @GetMapping
    public List<UsuarioResponseDTO> listarUsuarios(){
        System.out.println("Recibiendo la lista de todos los usuarios");
        return usuarioService.obtenerTodosLosUsuarios();
    }

    @PutMapping("/{id}")
    public Usuario actualizarUsuario(@PathVariable Long id, @RequestBody Usuario datosNuevos){
        System.out.println("Petición para actualizar al usuario " + id);
        return usuarioService.actualizarUsuario(id, datosNuevos);
    }

    @DeleteMapping("/{id}")
    public String borrarUsuario(@PathVariable Long id){
        System.out.println("Petición para borrar al usuario " + id);
        usuarioService.borrarUsuario(id);
        return "Usuario con ID " + id + " eliminado correctamente.";
    }
}