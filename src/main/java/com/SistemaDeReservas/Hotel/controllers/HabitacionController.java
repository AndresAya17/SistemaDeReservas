package com.SistemaDeReservas.Hotel.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<?> savePiso(@RequestBody Habitacion habitacion, BindingResult bindingResult) {

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

    @GetMapping("/piso/{idPiso}")
    public ResponseEntity<List<HabitacionDto>> obtenerHabitacionesPorPiso(@PathVariable Long idPiso) {
        List<HabitacionDto> habitaciones = habitacionService.findAllByIdPisos(idPiso);
        return ResponseEntity.ok(habitaciones);
    }
}
