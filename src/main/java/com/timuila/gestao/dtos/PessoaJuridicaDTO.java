package com.timuila.gestao.dtos;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Administrativo
 */
public record PessoaJuridicaDTO(
        UUID id,
        String nome,
        @NotNull
        String sobrenome,
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate abertura,
        @NotNull
        String cnpj,
        @NotNull
        String ie,
        String im) {

}
