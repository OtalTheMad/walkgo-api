package org.walkgo.api.controller;

import org.walkgo.api.model.Amigo;
import org.walkgo.api.service.AmigoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/amigos")
public class AmigosController {

    private final AmigoService amigosService;

    public AmigosController(AmigoService amigosService) {
        this.amigosService = amigosService;
    }

    @GetMapping
    public ResponseEntity<List<Amigo>> GetAllAmigos() {
        List<Amigo> _list = amigosService.GetAllAmigos();
        return ResponseEntity.ok(_list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Amigo> GetAmigoById(@PathVariable Integer id) {
        Optional<Amigo> _amigo = amigosService.GetAmigoById(id);
        return _amigo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Amigo>> GetAmigosByUsuario(@PathVariable Integer idUsuario) {
        List<Amigo> _list = amigosService.GetAmigosByUsuario(idUsuario);
        return ResponseEntity.ok(_list);
    }

    @PostMapping
    public ResponseEntity<Amigo> CreateAmigo(@RequestBody Amigo _amigo) {
        Amigo _new = amigosService.CreateAmigo(_amigo);
        return ResponseEntity.ok(_new);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Amigo> UpdateAmigo(
            @PathVariable Integer id,
            @RequestBody Amigo _details
    ) {
        Amigo _updated = amigosService.UpdateAmigo(id, _details);
        return ResponseEntity.ok(_updated);
    }
}