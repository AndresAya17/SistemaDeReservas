package com.SistemaDeReservas.Hotel.domain.repository;


import java.util.List;

import com.SistemaDeReservas.Hotel.domain.model.Piso;

public interface IPisoServicio {

    public List<Piso> findAll();

    public Piso buscarPisoPorId(Long id);

    public Piso agregarPiso(Piso piso);

    public void eliminarPiso(Long id);
}
