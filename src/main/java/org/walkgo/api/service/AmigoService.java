package org.walkgo.api.service;

import org.walkgo.api.model.Amigo;
import org.walkgo.api.repository.AmigoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AmigoService {

    private final AmigoRepository amigoRepository;

    public AmigoService(AmigoRepository amigoRepository) {
        this.amigoRepository = amigoRepository;
    }

    public List<Amigo> GetAllAmigos() {
        return amigoRepository.findAll();
    }

    public Optional<Amigo> GetAmigoById(Integer _id) {
        return amigoRepository.findById(_id);
    }

    public List<Amigo> GetAmigosByUsuario(Integer _idUsuario) {
        return amigoRepository.findByIdUsuario(_idUsuario);
    }

    public Amigo CreateAmigo(Amigo _amigo) {

        if (_amigo.getIdUsuario().equals(_amigo.getIdUsuarioAmigo())) {
            throw new IllegalArgumentException("Un usuario no puede agregarse a sí mismo como amigo.");
        }

        return amigoRepository.save(_amigo);
    }

    public Amigo UpdateAmigo(Integer _id, Amigo _details) {

        if (_details.getIdUsuario().equals(_details.getIdUsuarioAmigo())) {
            throw new IllegalArgumentException("Un usuario no puede agregarse a sí mismo como amigo.");
        }

        return amigoRepository.findById(_id).map(_amigo -> {
            _amigo.setIdUsuario(_details.getIdUsuario());
            _amigo.setIdUsuarioAmigo(_details.getIdUsuarioAmigo());
            _amigo.setEstado(_details.getEstado());
            return amigoRepository.save(_amigo);
        }).orElseGet(() -> {
            _details.setIdAmigo(_id);
            return amigoRepository.save(_details);
        });
    }
}