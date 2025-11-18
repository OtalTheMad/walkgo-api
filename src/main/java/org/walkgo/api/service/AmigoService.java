package org.walkgo.api.service;

import org.springframework.stereotype.Service;
import org.walkgo.api.model.Amigo;
import org.walkgo.api.repository.AmigoRepository;

import java.util.ArrayList;
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

        List<Amigo> _rows = amigoRepository.findByIdUsuarioOrIdUsuarioAmigo(_idUsuario, _idUsuario);
        List<Amigo> _result = new ArrayList<>();

        for (Amigo _row : _rows) {

            Integer _otroId = _row.getIdUsuario().equals(_idUsuario)
                    ? _row.getIdUsuarioAmigo()
                    : _row.getIdUsuario();

            String _estado = _row.getEstado();

            if ("solicitud_enviada".equals(_estado)) {
                if (_row.getIdUsuario().equals(_idUsuario)) {
                    _estado = "solicitud_enviada";
                } else {
                    _estado = "solicitud_recibida";
                }
            }

            Amigo _dto = new Amigo();
            _dto.setIdAmigo(_row.getIdAmigo());
            _dto.setIdUsuario(_idUsuario);
            _dto.setIdUsuarioAmigo(_otroId);
            _dto.setEstado(_estado);

            _result.add(_dto);
        }

        return _result;
    }

    public Amigo CreateAmigo(Amigo _amigo) {

        Integer _idUsuario = _amigo.getIdUsuario();
        Integer _idUsuarioAmigo = _amigo.getIdUsuarioAmigo();

        if (_idUsuario == null || _idUsuarioAmigo == null) {
            throw new IllegalArgumentException("Faltan ids de usuario");
        }

        if (_idUsuario.equals(_idUsuarioAmigo)) {
            throw new IllegalArgumentException("No puedes agregarte a ti mismo");
        }

        Optional<Amigo> _existingOpt = amigoRepository.findByUsuarios(_idUsuario, _idUsuarioAmigo);

        if (_existingOpt.isPresent()) {
            Amigo _existing = _existingOpt.get();
            String _estadoActual = _existing.getEstado();

            if ("solicitud_enviada".equals(_estadoActual)) {
                if (!_existing.getIdUsuario().equals(_idUsuario)) {
                    _existing.setEstado("activo");
                    return amigoRepository.save(_existing);
                }
                return _existing;
            }

            if ("activo".equals(_estadoActual)) {
                return _existing;
            }

            _existing.setIdUsuario(_idUsuario);
            _existing.setIdUsuarioAmigo(_idUsuarioAmigo);
            _existing.setEstado("solicitud_enviada");
            return amigoRepository.save(_existing);
        }

        _amigo.setEstado("solicitud_enviada");
        return amigoRepository.save(_amigo);
    }

    public Amigo UpdateAmigo(Integer _id, Amigo _details) {

        Amigo _existing = amigoRepository.findById(_id)
                .orElseThrow(() -> new RuntimeException("Amigo no encontrado"));

        String _nuevoEstado = _details.getEstado();
        if (_nuevoEstado == null) {
            return _existing;
        }

        if ("solicitud_recibida".equals(_nuevoEstado)) {
            _existing.setEstado("solicitud_enviada");
        } else {
            _existing.setEstado(_nuevoEstado);
        }

        return amigoRepository.save(_existing);
    }
}