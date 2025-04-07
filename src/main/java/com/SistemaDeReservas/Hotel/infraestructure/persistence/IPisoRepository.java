package com.SistemaDeReservas.Hotel.infraestructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SistemaDeReservas.Hotel.domain.model.Piso;

public interface IPisoRepository  extends JpaRepository<Piso, Long> {

    boolean existsByNombre(String nombre);
}
