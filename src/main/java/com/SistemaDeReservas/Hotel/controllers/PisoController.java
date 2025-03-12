package com.SistemaDeReservas.Hotel.controllers;

import com.SistemaDeReservas.Hotel.exceptions.RecursoNoEncontradoException;
import com.SistemaDeReservas.Hotel.models.Piso;
import com.SistemaDeReservas.Hotel.services.PisoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("reservas-app")
public class PisoController {

    private static final Logger logger = LoggerFactory.getLogger(PisoController.class);

    @Autowired
    private PisoServiceImpl pisoService;

    @GetMapping("/pisos")
    public List<Piso> obtenerPiso(){
        return pisoService.findAll();
        // List<PisoDto> pisosDto = this.pisoService.findAll();
        // logger.info("Pisos obtenidos");
        // pisosDto.forEach((piso -> logger.info(pisosDto.toString())));
        // return pisosDto;
    }

    @PostMapping("/pisos")
    public ResponseEntity<?> crearPiso(@RequestBody Piso piso){
        try {
            Piso pisoCreado = pisoService.agregarPiso(piso);
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Piso creado correctamente");
            response.put("data", pisoCreado);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/pisos/{id}")
    public ResponseEntity<Piso> obtenerPisoId(
            @PathVariable Long id
    ){
        logger.info("Piso encontrado: " + id);
        Piso piso = this.pisoService.buscarPisoPorId(id);
        if (piso != null){
            return ResponseEntity.ok(piso);
        } else throw new RecursoNoEncontradoException("No se encontro el piso: " + id);
    }

    @PutMapping("/pisos/{id}")
    public ResponseEntity<Piso> actualizarPiso(
            @PathVariable Long id,
            @RequestBody Piso pisoRecibido
    ){
        logger.info("Piso modificiado: " + id);
        Piso piso = this.pisoService.buscarPisoPorId(id);
        if (piso == null){
            throw new RecursoNoEncontradoException("No se encontro el piso: " + id);
        }
        piso.setNombre(pisoRecibido.getNombre());
        piso.setDescripcion(pisoRecibido.getDescripcion());
        this.pisoService.agregarPiso(piso);
        return ResponseEntity.ok(piso);
    }

    @DeleteMapping("/pisos/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarPiso(
            @PathVariable Long id
    ){
        Piso piso = pisoService.buscarPisoPorId(id);
        if (piso == null){
            throw new RecursoNoEncontradoException("No se encontro el piso: " + id);
        }
        logger.info("Piso eliminado: " + id);
        this.pisoService.eliminarPiso(piso.getId());
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}
