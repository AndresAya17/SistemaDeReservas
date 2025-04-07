package com.SistemaDeReservas.Hotel.infraestructure.persistence;

import com.SistemaDeReservas.Hotel.domain.model.Piso;
import com.SistemaDeReservas.Hotel.domain.repository.IPisoServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PisoServiceImpl implements IPisoServicio {

    @Autowired
    private IPisoRepository pisoRepository;

    @Override
    public List<Piso> findAll() {
        return pisoRepository.findAll();
    }

    @Override
    public Piso buscarPisoPorId(Long id) {
        Piso piso = this.pisoRepository.findById(id).orElse(null);
        return piso;
    }

    @Override
    public Piso agregarPiso(Piso piso) {
        if (pisoRepository.existsByNombre(piso.getNombre())){
            throw new IllegalArgumentException("Ya existe el piso " + piso.getNombre());
        }
        return this.pisoRepository.save(piso);
    }

    @Override
    public void eliminarPiso(Long id) {
        this.pisoRepository.deleteById(id);
    }
}
