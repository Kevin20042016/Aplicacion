package com.fitnessapp.service;

import com.fitnessapp.dto.UsuarioRequestDTO;
import com.fitnessapp.dto.UsuarioResponseDTO;
import com.fitnessapp.model.Genero;
import com.fitnessapp.model.NivelActividad;
import com.fitnessapp.model.Objetivo;
import com.fitnessapp.model.Usuario;
import com.fitnessapp.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    void registrarUsuario_DebeDevolverDtoConDatosYCalorias(){

        //1. ARRANGE (Preparar el escenario)
        UsuarioRequestDTO peticionFalsa = new UsuarioRequestDTO(
                "Kevin", "Prueba", 25, 75.0, 175.0, Genero.MASCULINO, NivelActividad.MODERADO, Objetivo.MANTENER, "ninguno"
        );

        Usuario usuarioFalsoGuardado = new Usuario();
        usuarioFalsoGuardado.setId(1L);
        usuarioFalsoGuardado.setNombre("Kevin");
        usuarioFalsoGuardado.setObjetivo(Objetivo.MANTENER);
        usuarioFalsoGuardado.setCaloriasRecomendadas(2500.0);

        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuarioFalsoGuardado);

        //2. ACT (Actuar)

        UsuarioResponseDTO resultado = usuarioService.registrarUsuario(peticionFalsa);


        //3. ASSERT (Afirmar / Comprobar)

        //Comprobar que el resultado no sea nulo
        assertNotNull(resultado, "El resultado bo debería ser nulo");

        //Comprobar que el nombre de la caja de salida es el correcto
        assertEquals("Kevin", resultado.nombre(), "El nombre debería coincidir");

        //Comprobar que el ID se haya inyectado bien
        assertEquals(1L, resultado.id(), "El ID debería ser 1");

        //Verificamos que el Chef haya llamado al Almacenero exacatamente 1 vez
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }
}