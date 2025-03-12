package com.SistemaDeReservas.Hotel.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.SistemaDeReservas.Hotel.enums.EstadoHabitacion;
import com.SistemaDeReservas.Hotel.enums.TipoHabitacion;
import jakarta.validation.Valid;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.SistemaDeReservas.Hotel.dto.HabitacionDto;
import com.SistemaDeReservas.Hotel.exceptions.UserNotFoundException;
import com.SistemaDeReservas.Hotel.models.Habitacion;
import com.SistemaDeReservas.Hotel.services.IHabitacionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("reservas-app/habitacion")
@RequiredArgsConstructor
public class HabitacionController {

    private final IHabitacionService habitacionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> saveHabitacion(@Valid @RequestBody Habitacion habitacion, BindingResult bindingResult) {
        try {
            Map<String, Object> response = new HashMap<>();
            if (bindingResult.hasFieldErrors()) {
                List<String> errors = bindingResult.getFieldErrors().stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .toList();
                response.put("Message", errors.toString());
                return ResponseEntity.badRequest().body(errors);
            }
            response.put("Message", "Exitoso");
            habitacionService.save(habitacion);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(habitacionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return ResponseEntity.ok(habitacionService.findHabitacion(id));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException ex) {
        // Retorna un 404 con el mensaje de la excepción
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id){
        habitacionService.deleteById(id);
    }

    @GetMapping("/filtrar")
    public ResponseEntity<List<HabitacionDto>> filtrarHabitacion(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) TipoHabitacion tipo,
            @RequestParam(required = false) Double precioMin,
            @RequestParam(required = false) Double precioMax,
            @RequestParam(required = false) EstadoHabitacion estado) {

        List<HabitacionDto> habitaciones = habitacionService.filtrarHabitacion(nombre, tipo, precioMin, precioMax, estado);
        return ResponseEntity.ok(habitaciones);
    }
}