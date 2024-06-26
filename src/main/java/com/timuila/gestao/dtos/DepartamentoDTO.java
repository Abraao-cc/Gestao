package com.timuila.gestao.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.UUID;

/**
 *
 * @author Administrativo
 */
public record DepartamentoDTO(
        UUID id,
        @NotNull
        @Size(max = 255)
        String nome) {

}
