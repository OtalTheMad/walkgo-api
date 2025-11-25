package org.walkgo.api.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.walkgo.api.model.RankingResponse;
import org.walkgo.api.model.RankingEntry;
import org.walkgo.api.model.Usuario;
import org.walkgo.api.repository.RankingRepository;
import org.walkgo.api.repository.UsuarioRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RankingService {

    private final RankingRepository rankingRepository;
    private final UsuarioRepository usuarioRepository;

    public RankingService(RankingRepository rankingRepository, UsuarioRepository usuarioRepository) {
        this.rankingRepository = rankingRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public void refreshRanking() {
        rankingRepository.insertMissingUsers();

        List<Usuario> usuarios = usuarioRepository.findAll();
        usuarios.sort((a, b) -> {
            Double da = a.getTotalDistanciaKm() == null ? 0.0 : a.getTotalDistanciaKm();
            Double db = b.getTotalDistanciaKm() == null ? 0.0 : b.getTotalDistanciaKm();
            int cmp = Double.compare(db, da);
            if (cmp != 0) return cmp;
            Integer ia = a.getId() == null ? 0 : a.getId();
            Integer ib = b.getId() == null ? 0 : b.getId();
            return Integer.compare(ia, ib);
        });

        Map<Integer, RankingEntry> existing = new HashMap<>();
        for (RankingEntry r : rankingRepository.findAll()) {
            if (r.getIdUsuario() != null && !existing.containsKey(r.getIdUsuario())) {
                existing.put(r.getIdUsuario(), r);
            }
        }

        List<RankingEntry> toSave = new ArrayList<>();
        int pos = 1;
        for (Usuario u : usuarios) {
            if (u.getId() == null) continue;

            RankingEntry r = existing.get(u.getId());
            if (r == null) {
                r = new RankingEntry();
                r.setIdUsuario(u.getId());
            }

            r.setPosicion(pos);
            r.setEstado("activo");
            toSave.add(r);
            pos++;
        }

        rankingRepository.saveAll(toSave);
    }

    @Transactional(readOnly = true)
    public List<RankingResponse> getRanking() {
        refreshRanking();

        List<RankingEntry> ranking = rankingRepository.findAllByOrderByPosicionAsc();
        if (ranking.isEmpty()) {
            return List.of();
        }

        Map<Integer, Usuario> usuariosById = usuarioRepository.findAllById(
                ranking.stream()
                        .map(RankingEntry::getIdUsuario)
                        .filter(Objects::nonNull)
                        .collect(Collectors.toSet())
        ).stream().collect(Collectors.toMap(Usuario::getId, u -> u));

        List<RankingResponse> out = new ArrayList<>();
        for (RankingEntry r : ranking) {
            Usuario u = r.getIdUsuario() == null ? null : usuariosById.get(r.getIdUsuario());
            if (u == null) continue;

            Double km = u.getTotalDistanciaKm() == null ? 0.0 : u.getTotalDistanciaKm();
            out.add(new RankingResponse(
                    r.getPosicion(),
                    u.getId(),
                    u.getUsuario(),
                    km
            ));
        }
        return out;
    }
}