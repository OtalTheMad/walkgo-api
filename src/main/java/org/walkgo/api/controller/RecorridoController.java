package org.walkgo.api.controller;

import org.springframework.web.bind.annotation.*;
import org.walkgo.api.model.FinalizarRecorridoRequest;
import org.walkgo.api.model.Recorrido;
import org.walkgo.api.model.Usuario;
import org.walkgo.api.service.RecorridoService;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class RecorridoController {

    private final RecorridoService recorridoService;

    public RecorridoController(RecorridoService recorridoService) {
        this.recorridoService = recorridoService;
    }

    @PostMapping("/{id}/recorridos/finalizar")
    public Usuario finalizarRecorrido(
            @PathVariable("id") Integer idUsuario,
            @RequestBody FinalizarRecorridoRequest req
    ) {
        return recorridoService.finalizarRecorrido(idUsuario, req);
    }

    @GetMapping("/{id}/recorridos/semana")
    public List<Recorrido> getRecorridosSemana(
            @PathVariable("id") Integer idUsuario
    ) {
        return recorridoService.getRecorridosSemana(idUsuario);
    }
}