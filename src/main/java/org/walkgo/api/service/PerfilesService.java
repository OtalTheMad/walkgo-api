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
        List<Perfiles> _perfilesList = perfilesRepository.findAll();
        return _perfilesList;
    }

    public Optional<Perfiles> GetPerfilById(int _id) {
        Optional<Perfiles> _perfil = perfilesRepository.findById(_id);
        return _perfil;
    }
}