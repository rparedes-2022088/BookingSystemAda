package org.adaschool.BookingSystem;

import org.adaschool.BookingSystem.model.Reserva;
import org.adaschool.BookingSystem.model.Usuario;
import org.adaschool.BookingSystem.repository.reserva.ReservaRepository;
import org.adaschool.BookingSystem.repository.usuario.UsuarioRepository;
import org.adaschool.BookingSystem.service.reserva.ReservaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ReservaTest {
    @InjectMocks
    private ReservaServiceImpl reservaService;

    @Mock
    private ReservaRepository reservaRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    private Reserva reserva;
    private Usuario usuario;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        usuario = new Usuario("1", "Usuario Test", "test@gmail.cmo", "dsafsdfds", "47876343");
        reserva = new Reserva("1", "1", LocalDateTime.now());
    }

    @Test
    void testFindAll() {
        when(reservaRepository.findAll()).thenReturn(Collections.singletonList(reserva));

        assertEquals(1, reservaService.findAll().size());
    }

    @Test
    void testFindById_ExistingId() {
        when(reservaRepository.findById("1")).thenReturn(Optional.of(reserva));

        Reserva foundReserva = reservaService.findById("1");
        assertEquals(reserva, foundReserva);
    }

    @Test
    void testFindById_NonExistingId() {
        when(reservaRepository.findById("2")).thenReturn(Optional.empty());

        Reserva foundReserva = reservaService.findById("2");
        assertNull(foundReserva);
    }

    @Test
    void testSave_ValidReserva() {
        when(usuarioRepository.findById("1")).thenReturn(Optional.of(usuario));
        when(reservaRepository.save(any(Reserva.class))).thenReturn(reserva);

        Reserva savedReserva = reservaService.save(reserva);
        assertEquals(reserva, savedReserva);
        verify(reservaRepository).save(reserva);
    }

    @Test
    void testSave_NonExistingUser() {
        when(usuarioRepository.findById("1")).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            reservaService.save(reserva);
        });
        assertEquals("El usuario con ID 1 no existe.", exception.getMessage());
    }

    @Test
    void testUpdate_ValidReserva() {
        when(reservaRepository.findById("1")).thenReturn(Optional.of(reserva));
        when(usuarioRepository.findById("1")).thenReturn(Optional.of(usuario));
        when(reservaRepository.save(any(Reserva.class))).thenReturn(reserva);

        reserva.setUsuarioId("1");
        Reserva updatedReserva = reservaService.update("1", reserva);
        assertEquals(reserva, updatedReserva);
        verify(reservaRepository).save(reserva);
    }

    @Test
    void testUpdate_NonExistingReserva() {
        when(reservaRepository.findById("1")).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            reservaService.update("1", reserva);
        });
        assertEquals("Reserva con ID 1 no encontrada.", exception.getMessage());
    }

    @Test
    void testDeleteById_ExistingId() {
        reservaService.deleteById("1");
        verify(reservaRepository).deleteById("1");
    }

    @Test
    void testGetReservaById_ExistingId() {
        when(reservaRepository.findById("1")).thenReturn(Optional.of(reserva));
        when(usuarioRepository.findById("1")).thenReturn(Optional.of(usuario));

        Reserva reserva1 = reservaService.findById("1");
        assertEquals(reserva.getId(), reserva1.getId());
        assertEquals(usuario, reserva1.getUsuarioId());
    }

    @Test
    void testGetReservaById_NonExistingReserva() {
        when(reservaRepository.findById("1")).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            reservaService.findById("1");
        });
        assertEquals("Reserva no encontrada.", exception.getMessage());
    }

    @Test
    void testGetReservaById_NonExistingUser() {
        when(reservaRepository.findById("1")).thenReturn(Optional.of(reserva));
        when(usuarioRepository.findById("1")).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            reservaService.findById("1");
        });
        assertEquals("Usuario no encontrado para la reserva.", exception.getMessage());
    }
}
