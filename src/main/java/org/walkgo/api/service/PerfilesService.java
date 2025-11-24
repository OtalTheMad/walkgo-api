package org.walkgo.api.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.walkgo.api.model.Perfiles;
import org.walkgo.api.repository.PerfilesRepository;

@Service
public class PerfilesService {

    private final PerfilesRepository perfilesRepository;

    public PerfilesService(PerfilesRepository perfilesRepository) {
        this.perfilesRepository = perfilesRepository;
    }

    public List<Perfiles> GetAllPerfiles() {
        List<Perfiles> _list = perfilesRepository.findAll();
        return _list;
    }

    public Optional<Perfiles> GetPerfilById(Integer _id) {
        Optional<Perfiles> _perfil = perfilesRepository.findByIdUsuario(_id);
        return _perfil;
    }

    public Perfiles SavePerfil(Perfiles _perfil) {
        return perfilesRepository.save(_perfil);
    }

    public Perfiles UpdatePerfil(Integer _id, Perfiles _details) {
        return perfilesRepository.findById(_id).map(_perfil -> {
            _perfil.setId_usuario(_details.getId_usuario());
            _perfil.setFoto(_details.getFoto());
            _perfil.setPais(_details.getPais());
            _perfil.setBiografia(_details.getBiografia());
            _perfil.setFecha_nac(_details.getFecha_nac());
            _perfil.setEstado(_details.getEstado());
            return perfilesRepository.save(_perfil);
        }).orElseGet(() -> {
            _details.setId_perfil(_id);
            return perfilesRepository.save(_details);
        });
    }
}