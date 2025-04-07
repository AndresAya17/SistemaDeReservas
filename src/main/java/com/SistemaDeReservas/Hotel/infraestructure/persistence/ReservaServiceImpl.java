package com.SistemaDeReservas.Hotel.infraestructure.persistence;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.SistemaDeReservas.Hotel.common.dto.HabitacionDto;
import com.SistemaDeReservas.Hotel.common.dto.PisoDto;
import com.SistemaDeReservas.Hotel.common.dto.ReservaDto;
import com.SistemaDeReservas.Hotel.domain.enums.EstadoHabitacion;
import com.SistemaDeReservas.Hotel.domain.model.Habitacion;
import com.SistemaDeReservas.Hotel.domain.model.Reserva;
import com.SistemaDeReservas.Hotel.domain.repository.IReservaService;

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
        PisoDto pisoDto = PisoDto.builder()
                .nombre(reserva.getHabitacion().getPiso().getNombre())
                .descripcion(reserva.getHabitacion().getPiso().getDescripcion())
                .build();

        HabitacionDto habitacionDto = HabitacionDto.builder()
                .numero(reserva.getHabitacion().getNumero())
                .tipo(reserva.getHabitacion().getTipo())
                .precio(reserva.getHabitacion().getPrecio())
                .estado(reserva.getHabitacion().getEstado())
                .pisoDto(pisoDto)
                .build();

        return ReservaDto.builder()
                .fechaInicio(reserva.getFechaInicio())
                .fechaFin(reserva.getFechaFin())
                .estado(reserva.getEstado())
                .habitacionDto(habitacionDto)
                .build();
    }

    @Override
    public Reserva buscarReservaPorId(Long id) {
        Reserva reserva = reservaRepository.findById(id).orElse(null);
        return reserva;
    }

    @Override
    public Reserva agregarReserva(Reserva reserva) {
        Long idHabitacion = reserva.getHabitacion().getId();
        Habitacion habitacion = habitacionRepository.findById(idHabitacion)
                .orElseThrow(() -> new RuntimeException("Habitación no encontrada"));
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
