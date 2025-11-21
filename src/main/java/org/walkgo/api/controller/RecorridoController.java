package org.walkgo.api.controller;

import org.springframework.web.bind.annotation.*;
import org.walkgo.api.model.FinalizarRecorridoRequest;
import org.walkgo.api.model.Usuario;
import org.walkgo.api.service.RecorridoService;

@RestController
@RequestMapping("/api/usuarios")
public class RecorridoController {

    private final RecorridoService recorridoService;

    public RecorridoController(RecorridoService recorridoService) {
        this.recorridoService = recorridoService;
    }

    @PostMapping("/{id}/recorridos/finalizar")
    public Usuario FinalizarRecorrido(
            @PathVariable("id") Integer _idUsuario,
            @RequestBody FinalizarRecorridoRequest _req
    ) {
        return recorridoService.FinalizarRecorrido(_idUsuario, _req);
    }
}