package org.walkgo.api.service;

import org.springframework.stereotype.Service;
import org.walkgo.api.model.FinalizarRecorridoRequest;
import org.walkgo.api.model.Recorrido;
import org.walkgo.api.model.Usuario;
import org.walkgo.api.repository.RecorridoRepository;
import org.walkgo.api.repository.UsuarioRepository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class RecorridoService {

    private final RecorridoRepository recorridoRepository;
    private final UsuarioRepository usuarioRepository;

    public RecorridoService(RecorridoRepository recorridoRepository, UsuarioRepository usuarioRepository) {
        this.recorridoRepository = recorridoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario FinalizarRecorrido(Integer _idUsuario, FinalizarRecorridoRequest _req) {

        Usuario _usuario = usuarioRepository.findById(_idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Double _distanciaSesion = _req.GetDistanciaSesionKm();
        if (_distanciaSesion == null) {
            _distanciaSesion = 0.0;
        }

        Integer _pasosSesion = _req.GetPasosSesion();
        if (_pasosSesion == null) {
            _pasosSesion = 0;
        }

        Recorrido _recorrido = new Recorrido();
        _recorrido.SetIdUsuario(_idUsuario);
        _recorrido.SetDistanciaKm(_distanciaSesion);
        _recorrido.SetPasos(_pasosSesion);
        _recorrido.SetFecha(LocalDateTime.now());
        recorridoRepository.save(_recorrido);

        Double _totalDistanciaActual = _usuario.getTotalDistanciaKm();
        if (_totalDistanciaActual == null) {
            _totalDistanciaActual = 0.0;
        }
        _usuario.setTotalDistanciaKm(_totalDistanciaActual + _distanciaSesion);

        Integer _totalPasosActual = _usuario.getTotalPasos();
        if (_totalPasosActual == null) {
            _totalPasosActual = 0;
        }
        _usuario.setTotalPasos(_totalPasosActual + _pasosSesion);

        Integer _totalPasosSemanaActual = _usuario.getTotalPasosSemanales();
        if (_totalPasosSemanaActual == null) {
            _totalPasosSemanaActual = 0;
        }
        _usuario.setTotalPasosSemanales(_totalPasosSemanaActual + _pasosSesion);

        LocalDate _hoy = LocalDate.now();
        LocalDate _inicioSemana = _hoy.with(DayOfWeek.MONDAY);
        LocalDate _finSemana = _inicioSemana.plusDays(6);

        LocalDateTime _inicio = _inicioSemana.atStartOfDay();
        LocalDateTime _fin = _finSemana.atTime(23, 59, 59);

        List<Recorrido> _recorridosSemana = recorridoRepository.findByIdUsuarioAndFechaBetween(
                _idUsuario,
                _inicio,
                _fin
        );

        double _sumaKmSemana = 0.0;
        for (Recorrido _r : _recorridosSemana) {
            if (_r.GetDistanciaKm() != null) {
                _sumaKmSemana += _r.GetDistanciaKm();
            }
        }

        int _rangoSemanal = (int) Math.round(_sumaKmSemana);
        _usuario.setRangoSemanal(_rangoSemanal);
        _usuario.setActualizadoEn(LocalDateTime.now());

        return usuarioRepository.save(_usuario);
    }

    public List<Recorrido> GetRecorridosSemana(Integer _idUsuario) {

        LocalDate _hoy = LocalDate.now();
        LocalDate _inicioSemana = _hoy.with(DayOfWeek.MONDAY);
        LocalDate _finSemana = _inicioSemana.plusDays(6);

        LocalDateTime _inicio = _inicioSemana.atStartOfDay();
        LocalDateTime _fin = _finSemana.atTime(23, 59, 59);

        return recorridoRepository.findByIdUsuarioAndFechaBetween(
                _idUsuario,
                _inicio,
                _fin
        );
    }
}