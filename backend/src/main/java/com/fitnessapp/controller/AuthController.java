package com.fitnessapp.controller;

import com.fitnessapp.dto.LoginRequestDTO;
import com.fitnessapp.dto.UsuarioResponseDTO;
import com.fitnessapp.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {
    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
            this.usuarioService = usuarioService;
    }

    @GetMapping("/verificar")
        public String verificarCorreo(@RequestParam String email){
            return usuarioService.verificarUsuario(email);
    }

    // ENDPOINT: Iniciar sesión (Verbo POST por seguridad)
    @PostMapping("/login")
    public UsuarioResponseDTO iniciarSesion(@RequestBody LoginRequestDTO loginDTO) {
        // Le pasamos la cesta con los datos al Guardia de Seguridad (Service)
        return usuarioService.iniciarSesion(loginDTO);
    }
}
