package org.adaschool.BookingSystem.service.reserva;

import org.adaschool.BookingSystem.model.Reserva;

import java.util.List;

public interface ReservaService {
    List<Reserva> findAll();
    Reserva findById(String id);
    Reserva save(Reserva reserva);
    Reserva update(String id, Reserva reserva);
    void deleteById(String id);
}
