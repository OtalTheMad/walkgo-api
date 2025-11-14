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
        List<Amigo> _list = amigoRepository.findAll();
        return _list;
    }

    public Optional<Amigo> GetAmigoById(Integer _id) {
        Optional<Amigo> _amigo = amigoRepository.findById(_id);
        return _amigo;
    }

    public List<Amigo> GetAmigosByUsuario(Integer _idUsuario) {
        List<Amigo> _list = amigoRepository.findByIdUsuario(_idUsuario);
        return _list;
    }

    public Amigo CreateAmigo(Amigo _amigo) {
        return amigoRepository.save(_amigo);
    }

    public Amigo UpdateAmigo(Integer _id, Amigo _details) {
        return amigoRepository.findById(_id).map(_amigo -> {
            _amigo.SetId_Usuario(_details.GetId_Usuario());
            _amigo.SetId_Usuario_Amigo(_details.GetId_Usuario_Amigo());
            _amigo.SetEstado(_details.GetEstado());
            return amigoRepository.save(_amigo);
        }).orElseGet(() -> {
            _details.SetId_Amigo(_id);
            return amigoRepository.save(_details);
        });
    }
}