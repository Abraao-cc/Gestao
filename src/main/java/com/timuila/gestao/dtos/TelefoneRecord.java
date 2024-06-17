package com.timuila.gestao.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

/**
 *
 * @author Administrativo
 */
public record TelefoneRecord(
        UUID id,
        @NotBlank
        @NotNull
        String numero,
        @NotBlank
        @NotNull
        String tipo,
        @NotBlank
        @NotNull
        String pessoa) {

}
