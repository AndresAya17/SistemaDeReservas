package com.SistemaDeReservas.Hotel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SistemaDeReservas.Hotel.models.Reserva;

public interface IReservaRepository extends JpaRepository<Reserva, Long>{

    
}
