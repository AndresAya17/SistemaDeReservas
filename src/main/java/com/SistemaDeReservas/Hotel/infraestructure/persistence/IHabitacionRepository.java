package com.SistemaDeReservas.Hotel.infraestructure.persistence;

import com.SistemaDeReservas.Hotel.domain.enums.EstadoHabitacion;
import com.SistemaDeReservas.Hotel.domain.enums.TipoHabitacion;
import com.SistemaDeReservas.Hotel.domain.model.Habitacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IHabitacionRepository extends JpaRepository<Habitacion, Long>{
    //Una entidad -> Optional; Muchas -> List

    // Consulta para filtar las habitaciones
    @Query("SELECT h FROM Habitacion h WHERE " +
            "(:numero IS NULL OR h.numero LIKE %:numero%) " +
            "AND (:tipo IS NULL OR h.tipo = :tipo) " +
            "AND (:precioMin IS NULL OR h.precio >= :precioMin) " +
            "AND (:precioMax IS NULL OR h.precio <= :precioMax) " +
            "AND (:estado IS NULL OR h.estado = :estado)")
    List<Habitacion> filtrarHabitacion(
            @Param("numero") String numero,
            @Param("tipo") TipoHabitacion tipo,
            @Param("precioMin") Double precioMin,
            @Param("precioMax") Double precioMax,
            @Param("estado") EstadoHabitacion estado
    );

    boolean existsByNumero(String numero);

}
