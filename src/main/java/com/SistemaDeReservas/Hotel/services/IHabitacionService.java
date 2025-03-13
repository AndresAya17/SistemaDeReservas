package com.SistemaDeReservas.Hotel.services;

import java.util.List;

import com.SistemaDeReservas.Hotel.dto.HabitacionDto;
import com.SistemaDeReservas.Hotel.enums.EstadoHabitacion;
import com.SistemaDeReservas.Hotel.enums.TipoHabitacion;
import com.SistemaDeReservas.Hotel.models.Habitacion;

public interface IHabitacionService {
    void save (Habitacion habitacion);

    List<HabitacionDto> findAll();
    
    Habitacion findHabitacion(Long id);

    void deleteById(Long id);

//    List<HabitacionDto> findAllByIdPisos(Long idPiso);
//
//    List<HabitacionDto> findByTipoAndEstado(TipoHabitacion tipo, EstadoHabitacion estado);

    List<HabitacionDto> filtrarHabitacion(String numero, TipoHabitacion tipo, Double precioMin, Double precioMax, EstadoHabitacion estado);
} 