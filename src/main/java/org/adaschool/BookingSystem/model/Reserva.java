package org.adaschool.BookingSystem.model;

import java.time.LocalDateTime;

public class Reserva {

    private String id;
    private String usuarioId;
    private LocalDateTime fechaReserva;

    public Reserva() {
    }

    public Reserva(String id, String usuarioId, LocalDateTime fechaReserva) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.fechaReserva = fechaReserva;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public LocalDateTime getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDateTime fechaReserva) {
        this.fechaReserva = fechaReserva;
    }
}
