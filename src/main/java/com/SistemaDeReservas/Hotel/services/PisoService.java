package com.SistemaDeReservas.Hotel.services;

import com.SistemaDeReservas.Hotel.models.Piso;
import com.SistemaDeReservas.Hotel.repositories.IPisoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PisoService implements IPisoServicio {

    @Autowired
    private IPisoRepository pisoRepository;

    @Override
    public List<Piso> findAll() {
        return pisoRepository.findAll();
        // return StreamSupport.stream(pisoRepository.findAll().spliterator(), false)
        // .map(piso -> new PisoDto(piso.getNombre(), piso.getDescripcion()))
        // .collect(Collectors.toList());
    }

    @Override
    public Piso buscarPisoPorId(Long id) {
        Piso piso = this.pisoRepository.findById(id).orElse(null);
        return piso;
    }

    @Override
    public Piso agregarPiso(Piso piso) {
        return this.pisoRepository.save(piso);
    }

    @Override
    public void eliminarPiso(Long id) {
        this.pisoRepository.deleteById(id);
    }
}
