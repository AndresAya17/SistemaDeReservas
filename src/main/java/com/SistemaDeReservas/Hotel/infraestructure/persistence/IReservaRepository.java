package com.SistemaDeReservas.Hotel.infraestructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SistemaDeReservas.Hotel.domain.model.Reserva;

public interface IReservaRepository extends JpaRepository<Reserva, Long>{

    
}
