package com.fitnessapp.service;

import com.fitnessapp.model.Usuario;
import com.fitnessapp.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;


    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    public Usuario registrarUsuario(Usuario nuevoUsuario) {

        System.out.println("El Chef está revisando al usuario: " + nuevoUsuario.getNombre());

        // Aquí es donde próximamente llamaremos a tus métodos de calcularCalorias()
        // ...

        System.out.println("Todo correcto. Mandando a guardar a la base de datos...");

        // Usamos el superpoder del Repository para guardarlo
        return usuarioRepository.save(nuevoUsuario);
    }
}
