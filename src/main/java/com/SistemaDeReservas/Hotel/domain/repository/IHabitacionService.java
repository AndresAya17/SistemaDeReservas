package com.SistemaDeReservas.Hotel.domain.repository;

import java.util.List;

import com.SistemaDeReservas.Hotel.common.dto.HabitacionDto;
import com.SistemaDeReservas.Hotel.domain.enums.EstadoHabitacion;
import com.SistemaDeReservas.Hotel.domain.enums.TipoHabitacion;
import com.SistemaDeReservas.Hotel.domain.model.Habitacion;

public interface IHabitacionService {
    void save (Habitacion habitacion);

    List<HabitacionDto> findAll();
    
    Habitacion findHabitacion(Long id);

    void deleteById(Long id);

    List<HabitacionDto> filtrarHabitacion(String numero, TipoHabitacion tipo, Double precioMin, Double precioMax, EstadoHabitacion estado);
} 