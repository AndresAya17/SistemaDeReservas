package com.SistemaDeReservas.Hotel.controllers;

import com.SistemaDeReservas.Hotel.exceptions.RecursoNoEncontradoException;
import com.SistemaDeReservas.Hotel.models.Piso;
import com.SistemaDeReservas.Hotel.services.PisoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    private PisoService pisoService;

    @GetMapping("/pisos")
    public List<Piso> obtenerPiso(){
        List<Piso> pisos = this.pisoService.listarPisos();
        logger.info("Pisos obtenidos");
        pisos.forEach((piso -> logger.info(piso.toString())));
        return pisos;
    }

    @PostMapping("/pisos")
    public Piso agregarPiso(@RequestBody Piso piso){
        logger.info("Piso agregado: " + piso);
        return this.pisoService.agregarPiso(piso);
    }

    @GetMapping("/pisos/{id}")
    public ResponseEntity<Piso> obtenerPisoId(
            @PathVariable int id
    ){
        logger.info("Piso encontrado: " + id);
        Piso piso = this.pisoService.buscarPisoPorId(id);
        if (piso != null){
            return ResponseEntity.ok(piso);
        } else throw new RecursoNoEncontradoException("No se encontro el piso: " + id);
    }

    @PutMapping("/pisos/{id}")
    public ResponseEntity<Piso> actualizarPiso(
            @PathVariable int id,
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
            @PathVariable int id
    ){
        Piso piso = pisoService.buscarPisoPorId(id);
        if (piso == null){
            throw new RecursoNoEncontradoException("No se encontro el piso: " + id);
        }
        logger.info("Piso eliminado: " + id);
        this.pisoService.eliminarPiso(piso.getIdPiso());
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}
