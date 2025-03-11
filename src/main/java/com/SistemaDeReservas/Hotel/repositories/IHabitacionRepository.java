package com.SistemaDeReservas.Hotel.repositories;

import com.SistemaDeReservas.Hotel.enums.EstadoHabitacion;
import com.SistemaDeReservas.Hotel.enums.TipoHabitacion;
import org.springframework.data.jpa.repository.JpaRepository;

import com.SistemaDeReservas.Hotel.models.Habitacion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IHabitacionRepository extends JpaRepository<Habitacion, Long>{
    //Una entidad -> Optional; Muchas -> List

//    @Query("SELECT h FROM Habitacion h WHERE h.piso.id = :idPiso")
//    List<Habitacion> findByPisoId(@Param("idPiso") Long idPiso);
//
//    @Query("SELECT h FROM Habitacion h WHERE h.tipo = :tipo AND h.estado = :estado")
//    List<Habitacion> findByTipoAndEstado(@Param("tipo") TipoHabitacion tipo, @Param("estado") EstadoHabitacion estado);

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

}
