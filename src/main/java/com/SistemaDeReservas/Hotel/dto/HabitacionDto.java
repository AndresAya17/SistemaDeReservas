package com.SistemaDeReservas.Hotel.dto;

import com.SistemaDeReservas.Hotel.enums.EstadoHabitacion;
import com.SistemaDeReservas.Hotel.enums.TipoHabitacion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class HabitacionDto {
    private String numero;
    private TipoHabitacion tipo;
    private Double precio;
    private EstadoHabitacion estado;
    private PisoDto pisoDto;
}