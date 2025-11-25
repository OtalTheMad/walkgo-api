package org.walkgo.api.service;

import org.springframework.stereotype.Service;
import org.walkgo.api.model.FinalizarRecorridoRequest;
import org.walkgo.api.model.Recorrido;
import org.walkgo.api.model.Usuario;
import org.walkgo.api.model.Estadisticas;
import org.walkgo.api.repository.RecorridoRepository;
import org.walkgo.api.repository.UsuarioRepository;
import org.walkgo.api.repository.EstadisticasRepository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class RecorridoService {

    private final RecorridoRepository recorridoRepository;
    private final UsuarioRepository usuarioRepository;
    private final EstadisticasRepository estadisticasRepository;

    public RecorridoService(RecorridoRepository recorridoRepository,
                            UsuarioRepository usuarioRepository,
                            EstadisticasRepository estadisticasRepository) {
        this.recorridoRepository = recorridoRepository;
        this.usuarioRepository = usuarioRepository;
        this.estadisticasRepository = estadisticasRepository;
    }

    public Usuario finalizarRecorrido(Integer idUsuario, FinalizarRecorridoRequest req) {

        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Double distanciaSesion = req.getDistanciaSesionKm();
        if (distanciaSesion == null) {
            distanciaSesion = 0.0;
        }

        Integer pasosSesion = req.getPasosSesion();
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
        Double nuevaDistanciaTotal = totalDistanciaActual + distanciaSesion;
        usuario.setTotalDistanciaKm(nuevaDistanciaTotal);

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

        usuario.setActualizadoEn(LocalDateTime.now());
        usuarioRepository.save(usuario);

        Estadisticas estadistica = estadisticasRepository.findByIdUsuario(idUsuario)
                .orElseGet(() -> {
                    Estadisticas nueva = new Estadisticas();
                    nueva.setId_usuario(idUsuario);
                    nueva.setKm_recorrido(0);
                    nueva.setCalorias_quemadas("0");
                    nueva.setClasificacion("principiante");
                    nueva.setEstado("activo");
                    return estadisticasRepository.save(nueva);
                });

        Double totalKmUsuario = usuario.getTotalDistanciaKm();
        if (totalKmUsuario == null) {
            totalKmUsuario = 0.0;
        }
        int kmEnterosTotales = (int) Math.floor(totalKmUsuario);
        estadistica.setKm_recorrido(kmEnterosTotales);
        estadisticasRepository.save(estadistica);

        return usuario;
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