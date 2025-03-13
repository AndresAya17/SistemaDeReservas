package com.SistemaDeReservas.Hotel.services;

import java.util.List;

import com.SistemaDeReservas.Hotel.dto.ReservaDto;
import com.SistemaDeReservas.Hotel.models.Reserva;

public interface IReservaService {
    public List<ReservaDto> findAll();

    public Reserva buscarReservaPorId(Long id);

    public Reserva agregarReserva(Reserva reserva);

    public void eliminarReserva(Long id);
}
