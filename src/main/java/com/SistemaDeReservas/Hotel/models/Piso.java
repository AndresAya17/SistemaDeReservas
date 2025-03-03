package com.SistemaDeReservas.Hotel.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Piso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idPiso;
    String nombre;
    String descripcion;
}
