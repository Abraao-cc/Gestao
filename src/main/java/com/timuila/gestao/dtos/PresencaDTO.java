package com.timuila.gestao.dtos;

import java.time.LocalDate;
import java.util.UUID;

/**
 *
 * @author Administrativo
 */
public record PresencaDTO(
        UUID id,
        LocalDate dataAtual,
        UUID funcionario) {

}
