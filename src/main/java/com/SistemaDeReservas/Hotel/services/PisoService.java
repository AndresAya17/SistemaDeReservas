package com.SistemaDeReservas.Hotel.services;

import com.SistemaDeReservas.Hotel.models.Piso;
import com.SistemaDeReservas.Hotel.repositories.IPisoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PisoService implements IPisoServicio {

    @Autowired
    private IPisoRepository repositorioPiso;

    @Override
    public List<Piso> listarPisos() {
        return this.repositorioPiso.findAll();
    }

    @Override
    public Piso buscarPisoPorId(Integer idPiso) {
        Piso piso = this.repositorioPiso.findById(idPiso).orElse(null);
        return piso;
    }

    @Override
    public Piso agregarPiso(Piso piso) {
        return this.repositorioPiso.save(piso);
    }

    @Override
    public void eliminarPiso(Integer idPiso) {
        this.repositorioPiso.deleteById(idPiso);
    }
}
