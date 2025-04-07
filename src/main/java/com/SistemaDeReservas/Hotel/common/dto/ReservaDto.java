package com.SistemaDeReservas.Hotel.common.dto;

import java.time.LocalDate;

import com.SistemaDeReservas.Hotel.domain.enums.EstadoReserva;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservaDto {
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private EstadoReserva estado;
    private HabitacionDto habitacionDto;
}
