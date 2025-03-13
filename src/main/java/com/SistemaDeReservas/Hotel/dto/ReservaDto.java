package com.SistemaDeReservas.Hotel.dto;

import java.time.LocalDate;

import com.SistemaDeReservas.Hotel.enums.EstadoReserva;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservaDto {
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private EstadoReserva estado;
    private HabitacionDto habitacionDto;
}
