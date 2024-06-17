package com.timuila.gestao.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

/**
 *
 * @author Administrativo
 */
public record EnderecoRecord(
        UUID id,
        @NotBlank
        @NotNull
        String uf,
        String cidade,
        @NotBlank
        @NotNull
        String bairro,
        @NotBlank
        @NotNull
        String rua,
        @NotBlank
        @NotNull
        String cep,
        @NotBlank
        @NotNull
        String numero,
        @NotBlank
        @NotNull
        String complemento,
        @NotBlank
        @NotNull
        String pessoa) {

}
