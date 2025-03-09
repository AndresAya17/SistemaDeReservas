package com.SistemaDeReservas.Hotel.services;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.SistemaDeReservas.Hotel.dto.HabitacionDto;
import com.SistemaDeReservas.Hotel.dto.PisoDto;
import com.SistemaDeReservas.Hotel.exceptions.UserNotFoundException;
import com.SistemaDeReservas.Hotel.models.Habitacion;
import com.SistemaDeReservas.Hotel.repositories.IHabitacionRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class HabitacionImpl implements IHabitacionService{

    private final IHabitacionRepository habitacionRepository;

    @Override
    public void save(Habitacion habitacion) {
        habitacionRepository.save(habitacion);
    }

    @Override
    public List<HabitacionDto> findAll() {
       return StreamSupport.stream(habitacionRepository.findAll().spliterator(), false)
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private HabitacionDto convertToDto(Habitacion habitacion) {
        PisoDto pisoDto = new PisoDto(habitacion.getPiso().getId());
        return new HabitacionDto(habitacion.getNumero(),habitacion.getTipo(),habitacion.getPrecio(),habitacion.getEstado(),pisoDto);
    }


    @Override
    public Habitacion findHabitacion(Long id) {
        return habitacionRepository.findById(id).orElseThrow(null);
    }

    @Override
    public void deleteById(Long id) {
        if(!habitacionRepository.existsById(id)){
            throw new UserNotFoundException(id);
        }
        habitacionRepository.deleteById(id);
    }

    @Override
    public List<HabitacionDto> findAllByIdPisos(Long idPiso) {
        List<Habitacion> habitacions = habitacionRepository.findByPisoId(idPiso);
        return habitacions.stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }
    
}
