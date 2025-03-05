package com.SistemaDeReservas.Hotel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SistemaDeReservas.Hotel.models.Habitacion;

public interface IHabitacionRepository extends JpaRepository<Habitacion, Long>{
    
}
