package org.walkgo.api.service;

import org.springframework.stereotype.Service;
import org.walkgo.api.model.Amigo;
import org.walkgo.api.model.Usuario;
import org.walkgo.api.repository.AmigoRepository;
import org.walkgo.api.repository.UsuarioRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AmigoService {

    private final AmigoRepository amigoRepository;
    private final UsuarioRepository usuarioRepository;

    public AmigoService(AmigoRepository amigoRepository, UsuarioRepository usuarioRepository) {
        this.amigoRepository = amigoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Amigo> GetAllAmigos() {
        return amigoRepository.findAll();
    }

    public Optional<Amigo> GetAmigoById(Integer _id) {
        return amigoRepository.findById(_id);
    }

    public List<Amigo> GetAmigosByUsuario(Integer _idUsuario) {
        List<Usuario> _usuarios = usuarioRepository.FindAllExceptId(_idUsuario);
        List<Amigo> _resultado = new ArrayList<>();

        for (Usuario _usuario : _usuarios) {
            Optional<Amigo> _existingOpt =
                    amigoRepository.findByIdUsuarioAndIdUsuarioAmigo(_idUsuario, _usuario.getId());

            if (_existingOpt.isPresent()) {
                _resultado.add(_existingOpt.get());
            } else {
                Amigo _nuevo = new Amigo();
                _nuevo.setIdUsuario(_idUsuario);
                _nuevo.setIdUsuarioAmigo(_usuario.getId());
                _nuevo.setEstado("no_siguiendo");
                _resultado.add(_nuevo);
            }
        }

        return _resultado;
    }

    public Amigo CreateAmigo(Amigo _amigo) {

        Integer _idUsuario = _amigo.getIdUsuario();
        Integer _idUsuarioAmigo = _amigo.getIdUsuarioAmigo();

        if (_idUsuario == null || _idUsuarioAmigo == null) {
            throw new IllegalArgumentException("Faltan ids de usuario");
        }

        if (_idUsuario.equals(_idUsuarioAmigo)) {
            throw new IllegalArgumentException("No puedes seguirte a ti mismo");
        }

        Optional<Amigo> _existingOpt =
                amigoRepository.findByIdUsuarioAndIdUsuarioAmigo(_idUsuario, _idUsuarioAmigo);

        if (_existingOpt.isPresent()) {
            Amigo _existing = _existingOpt.get();
            String _estadoActual = _existing.getEstado();

            if ("siguiendo".equals(_estadoActual)) {
                return _existing;
            }

            _existing.setEstado("siguiendo");
            return amigoRepository.save(_existing);
        }

        _amigo.setEstado("siguiendo");
        return amigoRepository.save(_amigo);
    }

    public Amigo UpdateAmigo(Integer _id, Amigo _details) {

        Amigo _existing = amigoRepository.findById(_id)
                .orElseThrow(() -> new RuntimeException("Relacion no encontrada"));

        String _nuevoEstado = _details.getEstado();
        if (_nuevoEstado != null) {
            _existing.setEstado(_nuevoEstado);
        }

        return amigoRepository.save(_existing);
    }
}