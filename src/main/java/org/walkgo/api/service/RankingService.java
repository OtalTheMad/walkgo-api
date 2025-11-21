package org.walkgo.api.service;

import org.springframework.stereotype.Service;
import org.walkgo.api.model.RankingEntry;
import org.walkgo.api.model.Usuario;
import org.walkgo.api.repository.UsuarioRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class RankingService {

    private final UsuarioRepository usuarioRepository;

    public RankingService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<RankingEntry> getRankingSemana() {
        List<Usuario> _usuarios = usuarioRepository.findAll();
        _usuarios.sort((a, b) -> {
            Integer _rangoA = a.getRangoSemanal();
            Integer _rangoB = b.getRangoSemanal();
            if (_rangoA == null) {
                _rangoA = 0;
            }
            if (_rangoB == null) {
                _rangoB = 0;
            }
            int _cmp = Integer.compare(_rangoB, _rangoA);
            if (_cmp != 0) {
                return _cmp;
            }
            Integer _idA = a.getId();
            Integer _idB = b.getId();
            if (_idA == null) {
                _idA = 0;
            }
            if (_idB == null) {
                _idB = 0;
            }
            return Integer.compare(_idA, _idB);
        });
        List<RankingEntry> _resultado = new ArrayList<>();
        int _posicion = 1;
        for (Usuario _usuario : _usuarios) {
            Integer _idUsuario = _usuario.getId();
            String _nombre = _usuario.getUsuario();
            Integer _rangoSemanal = _usuario.getRangoSemanal();
            if (_rangoSemanal == null) {
                _rangoSemanal = 0;
            }
            Double _totalKm = _usuario.getTotalDistanciaKm();
            if (_totalKm == null) {
                _totalKm = 0.0;
            }
            String _avatar = null;
            RankingEntry _entry = new RankingEntry(
                    _idUsuario,
                    _nombre,
                    _posicion,
                    _rangoSemanal,
                    _totalKm,
                    _avatar
            );
            _resultado.add(_entry);
            _posicion++;
        }
        return _resultado;
    }
}