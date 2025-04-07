package com.SistemaDeReservas.Hotel.infraestructure.controller;

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

import com.SistemaDeReservas.Hotel.common.exceptions.UserNotFoundException;
import com.SistemaDeReservas.Hotel.domain.model.Reserva;
import com.SistemaDeReservas.Hotel.domain.repository.IReservaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("reservas-app/reserva")
@RequiredArgsConstructor
public class ReservaController {

    private final IReservaService reservaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> saveReserva(@Valid @RequestBody Reserva reserva, BindingResult bindingResult) {
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
            reservaService.agregarReserva(reserva);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @GetMapping
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(reservaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return ResponseEntity.ok(reservaService.buscarReservaPorId(id));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException ex) {
        // Retorna un 404 con el mensaje de la excepción
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id){
        reservaService.eliminarReserva(id);
    }
}
