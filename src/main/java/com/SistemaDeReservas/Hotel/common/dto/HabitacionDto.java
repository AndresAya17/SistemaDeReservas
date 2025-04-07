package com.SistemaDeReservas.Hotel.common.dto;

import com.SistemaDeReservas.Hotel.domain.enums.EstadoHabitacion;
import com.SistemaDeReservas.Hotel.domain.enums.TipoHabitacion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HabitacionDto {
    private String numero;
    private TipoHabitacion tipo;
    private Double precio;
    private EstadoHabitacion estado;
    private PisoDto pisoDto;
}