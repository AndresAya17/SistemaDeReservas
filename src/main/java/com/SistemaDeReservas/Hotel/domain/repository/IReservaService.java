package com.SistemaDeReservas.Hotel.domain.repository;

import java.util.List;

import com.SistemaDeReservas.Hotel.common.dto.ReservaDto;
import com.SistemaDeReservas.Hotel.domain.model.Reserva;

public interface IReservaService {
    public List<ReservaDto> findAll();

    public Reserva buscarReservaPorId(Long id);

    public Reserva agregarReserva(Reserva reserva);

    public void eliminarReserva(Long id);
}
