package com.SistemaDeReservas.Hotel.services;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.SistemaDeReservas.Hotel.dto.HabitacionDto;
import com.SistemaDeReservas.Hotel.dto.PisoDto;
import com.SistemaDeReservas.Hotel.dto.ReservaDto;
import com.SistemaDeReservas.Hotel.enums.EstadoHabitacion;
import com.SistemaDeReservas.Hotel.models.Habitacion;
import com.SistemaDeReservas.Hotel.models.Reserva;
import com.SistemaDeReservas.Hotel.repositories.IHabitacionRepository;
import com.SistemaDeReservas.Hotel.repositories.IReservaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservaServiceImpl implements IReservaService {

    private final IReservaRepository reservaRepository;
    private final IHabitacionRepository habitacionRepository;

    @Override
    public List<ReservaDto> findAll() {
        return StreamSupport.stream(reservaRepository.findAll().spliterator(), false)
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private ReservaDto convertToDto(Reserva reserva) {
        PisoDto pisoDto = new PisoDto(reserva.getHabitacion().getPiso().getNombre(),reserva.getHabitacion().getPiso().getDescripcion());
        HabitacionDto habitacionDto = new HabitacionDto(reserva.getHabitacion().getNumero(),reserva.getHabitacion().getTipo(),reserva.getHabitacion().getPrecio(),reserva.getHabitacion().getEstado(),pisoDto);
        return new ReservaDto(reserva.getFechaInicio(),reserva.getFechaFin(),reserva.getEstado(),habitacionDto);
    }

    @Override
    public Reserva buscarReservaPorId(Long id) {
        Reserva reserva = reservaRepository.findById(id).orElse(null);
        return reserva;
    }

    @Override
    public Reserva agregarReserva(Reserva reserva) {
        Long idHabitacion = reserva.getHabitacion().getId();
        Habitacion habitacion = habitacionRepository.findById(idHabitacion).orElseThrow(() -> new RuntimeException("Habitación no encontrada"));
        habitacion.setEstado(EstadoHabitacion.RESERVADA);
        habitacionRepository.save(habitacion);
        reserva.setHabitacion(habitacion);
        return reservaRepository.save(reserva);
    }

    @Override
    public void eliminarReserva(Long id) {
        reservaRepository.deleteById(id);
    }

}
