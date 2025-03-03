package com.SistemaDeReservas.Hotel.repositories;

import com.SistemaDeReservas.Hotel.models.Piso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPisoRepository  extends JpaRepository<Piso, Integer> {
}
