package org.adaschool.BookingSystem.repository.reserva;

import org.adaschool.BookingSystem.model.Reserva;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ReservaRepository {
    private List<Reserva> reservas = new ArrayList<>();

    public List<Reserva> findAll() {
        return reservas;
    }

    public Optional<Reserva> findById(String id) {
        return reservas.stream().filter(reserva -> reserva.getId().equals(id)).findFirst();
    }

    public Reserva save(Reserva reserva) {
        reservas.add(reserva);
        return reserva;
    }

    public void deleteById(String id) {
        reservas.removeIf(reserva -> reserva.getId().equals(id));
    }
}