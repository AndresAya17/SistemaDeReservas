package com.SistemaDeReservas.Hotel.services;


import com.SistemaDeReservas.Hotel.models.Piso;

import java.util.List;

public interface IPisoServicio {

    public List<Piso> findAll();

    public Piso buscarPisoPorId(Long id);

    public Piso agregarPiso(Piso piso);

    public void eliminarPiso(Long id);
}
