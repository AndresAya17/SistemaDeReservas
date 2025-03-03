package com.SistemaDeReservas.Hotel.services;

import com.SistemaDeReservas.Hotel.dto.PisoDto;
import com.SistemaDeReservas.Hotel.models.Piso;
import com.SistemaDeReservas.Hotel.repositories.IPisoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PisoService implements IPisoServicio {

    @Autowired
    private IPisoRepository pisoRepository;

    @Override
    public List<PisoDto> findAll() {
        return StreamSupport.stream(pisoRepository.findAll().spliterator(), false)
        .map(piso -> new PisoDto(piso.getNombre(), piso.getDescripcion()))
        .collect(Collectors.toList());
    }

    @Override
    public Piso buscarPisoPorId(Integer idPiso) {
        Piso piso = this.pisoRepository.findById(idPiso).orElse(null);
        return piso;
    }

    @Override
    public Piso agregarPiso(Piso piso) {
        return this.pisoRepository.save(piso);
    }

    @Override
    public void eliminarPiso(Integer idPiso) {
        this.pisoRepository.deleteById(idPiso);
    }
}
