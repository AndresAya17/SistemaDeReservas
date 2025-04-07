package com.SistemaDeReservas.Hotel.infraestructure.persistence;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.SistemaDeReservas.Hotel.common.dto.HabitacionDto;
import com.SistemaDeReservas.Hotel.common.dto.PisoDto;
import com.SistemaDeReservas.Hotel.common.exceptions.UserNotFoundException;
import com.SistemaDeReservas.Hotel.domain.enums.EstadoHabitacion;
import com.SistemaDeReservas.Hotel.domain.enums.TipoHabitacion;
import com.SistemaDeReservas.Hotel.domain.model.Habitacion;
import com.SistemaDeReservas.Hotel.domain.repository.IHabitacionService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.server.ResponseStatusException;


@Service
@RequiredArgsConstructor
public class HabitacionServiceImpl implements IHabitacionService{

    private final IHabitacionRepository habitacionRepository;

    @Override
    public void save(Habitacion habitacion) {

        if (habitacionRepository.existsByNumero(habitacion.getNumero())){
            throw new IllegalArgumentException("Ya existe la habitacion " + habitacion.getNumero());
        }
        if (habitacion.getPrecio() <= 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El precio debe de ser mayor a 0");
        }
        habitacionRepository.save(habitacion);
    }

    @Override
    public List<HabitacionDto> findAll() {
       return StreamSupport.stream(habitacionRepository.findAll().spliterator(), false)
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private HabitacionDto convertToDto(Habitacion habitacion) {
        PisoDto pisoDto = new PisoDto(habitacion.getPiso().getNombre(),habitacion.getPiso().getDescripcion());
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
    public List<HabitacionDto> filtrarHabitacion(String numero, TipoHabitacion tipo, Double precioMin, Double precioMax, EstadoHabitacion estado){
        List<Habitacion> habitaciones = habitacionRepository.filtrarHabitacion(numero, tipo, precioMin, precioMax, estado);
        return habitaciones.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    
}
