package com.timuila.gestao.services;

import com.timuila.gestao.domain.Presenca;
import com.timuila.gestao.dtos.PresencaDTO;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Administrativo
 */
public interface PresencaService {

    Presenca save(PresencaDTO dto);

    void baterPonto(UUID funcionarioId, LocalTime horaAtual);

    PresencaDTO buscarPorId(UUID id);

    List<PresencaDTO> getPresencas();

    void delete(UUID id);
}
