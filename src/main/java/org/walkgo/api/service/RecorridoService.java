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

    public Usuario finalizarRecorrido(Integer idUsuario, FinalizarRecorridoRequest req) {

        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Double distanciaSesion = req.GetDistanciaSesionKm();
        if (distanciaSesion == null) {
            distanciaSesion = 0.0;
        }

        Integer pasosSesion = req.GetPasosSesion();
        if (pasosSesion == null) {
            pasosSesion = 0;
        }

        Recorrido recorrido = new Recorrido();
        recorrido.setIdUsuario(idUsuario);
        recorrido.setDistanciaKm(distanciaSesion);
        recorrido.setPasos(pasosSesion);
        recorrido.setFecha(LocalDateTime.now());
        recorridoRepository.save(recorrido);

        Double totalDistanciaActual = usuario.getTotalDistanciaKm();
        if (totalDistanciaActual == null) {
            totalDistanciaActual = 0.0;
        }
        usuario.setTotalDistanciaKm(totalDistanciaActual + distanciaSesion);

        Integer totalPasosActual = usuario.getTotalPasos();
        if (totalPasosActual == null) {
            totalPasosActual = 0;
        }
        usuario.setTotalPasos(totalPasosActual + pasosSesion);

        Integer totalPasosSemanaActual = usuario.getTotalPasosSemanales();
        if (totalPasosSemanaActual == null) {
            totalPasosSemanaActual = 0;
        }
        usuario.setTotalPasosSemanales(totalPasosSemanaActual + pasosSesion);

        LocalDate hoy = LocalDate.now();
        LocalDate inicioSemana = hoy.with(DayOfWeek.MONDAY);
        LocalDate finSemana = inicioSemana.plusDays(6);

        LocalDateTime inicio = inicioSemana.atStartOfDay();
        LocalDateTime fin = finSemana.atTime(23, 59, 59);

        List<Recorrido> recorridosSemana = recorridoRepository.findByIdUsuarioAndFechaBetween(
                idUsuario,
                inicio,
                fin
        );

        double sumaKmSemana = 0.0;
        for (Recorrido r : recorridosSemana) {
            if (r.getDistanciaKm() != null) {
                sumaKmSemana += r.getDistanciaKm();
            }
        }

        int rangoSemanal = (int) Math.round(sumaKmSemana);
        usuario.setRangoSemanal(rangoSemanal);
        usuario.setActualizadoEn(LocalDateTime.now());

        return usuarioRepository.save(usuario);
    }

    public List<Recorrido> getRecorridosSemana(Integer idUsuario) {

        LocalDate hoy = LocalDate.now();
        LocalDate inicioSemana = hoy.with(DayOfWeek.MONDAY);
        LocalDate finSemana = inicioSemana.plusDays(6);

        LocalDateTime inicio = inicioSemana.atStartOfDay();
        LocalDateTime fin = finSemana.atTime(23, 59, 59);

        return recorridoRepository.findByIdUsuarioAndFechaBetween(
                idUsuario,
                inicio,
                fin
        );
    }
}