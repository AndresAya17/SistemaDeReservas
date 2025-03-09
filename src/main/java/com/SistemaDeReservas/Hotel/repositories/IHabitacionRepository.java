package com.SistemaDeReservas.Hotel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.SistemaDeReservas.Hotel.models.Habitacion;

public interface IHabitacionRepository extends JpaRepository<Habitacion, Long>{

    @Query("SELECT h FROM Habitacion h WHERE h.piso.id = :idPiso")
    List<Habitacion> findByPisoId(@Param("idPiso") Long idPiso);
    
}
