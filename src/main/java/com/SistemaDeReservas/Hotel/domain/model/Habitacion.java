package com.SistemaDeReservas.Hotel.domain.model;

import com.SistemaDeReservas.Hotel.domain.enums.EstadoHabitacion;
import com.SistemaDeReservas.Hotel.domain.enums.TipoHabitacion;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "habitacion")
public class Habitacion {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "El numero de habitacion es obligatorio")
    private String numero;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull(message = "El tipo de habitacion no puede estar vacio")
    private TipoHabitacion tipo;

    @Column(nullable = false)
    @Min(value = 1, message = "El precio debe de ser mayor a 0")
    @NotNull(message = "El precio es obligatorio")
    private Double precio;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoHabitacion estado;
    
    @ManyToOne
    @JoinColumn(name = "id_piso", nullable = false) // Crea una FK llamada id_piso
    private Piso piso;
}

