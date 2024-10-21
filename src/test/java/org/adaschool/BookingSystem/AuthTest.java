package org.adaschool.BookingSystem;

import org.adaschool.BookingSystem.model.Usuario;
import org.adaschool.BookingSystem.repository.usuario.UsuarioRepository;
import org.adaschool.BookingSystem.security.JwtTokenUtil;
import org.adaschool.BookingSystem.service.auth.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AuthTest {
    @InjectMocks
    private AuthService authService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private JwtTokenUtil jwtTokenUtil;


    private Usuario usuario;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        usuario = new Usuario("1", "testUser", "test@example.com", "password", "98980909");
    }

    @Test
    void testLogin_ValidCredentials() {
        when(usuarioRepository.findByEmail("ruben@gmail.com")).thenReturn(usuario);
        when(jwtTokenUtil.generateToken("1")).thenReturn("token123");

        String token = authService.login("ruben@gmail.com", "dsfsdafds");
        assertEquals("token123", token);
        verify(usuarioRepository).findByEmail("test@example.com");
        verify(jwtTokenUtil).generateToken("1");
    }

    @Test
    void testLogin_InvalidEmail() {
        when(usuarioRepository.findByEmail("invalid@example.com")).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            authService.login("invalid@example.com", "password");
        });
        assertEquals("Invalid credentials", exception.getMessage());
    }

    @Test
    void testLogin_InvalidPassword() {
        when(usuarioRepository.findByEmail("test@example.com")).thenReturn(usuario);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            authService.login("test@example.com", "wrongpassword");
        });
        assertEquals("Invalid credentials", exception.getMessage());
    }

    @Test
    void testLogin_UserWithNullEmail() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            authService.login(null, "password");
        });
        assertEquals("Invalid credentials", exception.getMessage());
    }

    @Test
    void testLogin_UserWithNullPassword() {
        when(usuarioRepository.findByEmail("test@example.com")).thenReturn(usuario);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            authService.login("test@example.com", null);
        });
        assertEquals("Invalid credentials", exception.getMessage());
    }
}
