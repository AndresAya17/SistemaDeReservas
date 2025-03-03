package com.SistemaDeReservas.Hotel.services;

import com.SistemaDeReservas.Hotel.models.Piso;

import java.util.List;

public interface IPisoServicio {

    public List<Piso> listarPisos();

    public Piso buscarPisoPorId(Integer idPiso);

    public Piso agregarPiso(Piso piso);

    public void eliminarPiso(Integer idPiso);
}
