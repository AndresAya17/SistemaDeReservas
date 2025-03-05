package com.SistemaDeReservas.Hotel.services;

import java.util.List;

import com.SistemaDeReservas.Hotel.dto.HabitacionDto;
import com.SistemaDeReservas.Hotel.models.Habitacion;

public interface IHabitacionService {
    void save (Habitacion habitacion);

    List<HabitacionDto> findAll();
    
    Habitacion findHabitacion(Long id);

    void deleteById(Long id);
} 