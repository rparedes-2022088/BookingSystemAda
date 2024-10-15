package org.adaschool.BookingSystem.repository.reserva;

import org.adaschool.BookingSystem.model.Reserva;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservaRepository extends MongoRepository<Reserva, String> {

}