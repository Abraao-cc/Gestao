package com.timuila.gestao.dtos;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Administrativo
 */
public record FuncionarioResponseDTO(
        UUID id,
        String nome,
        @NotNull
        String sobrenome,
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate nascimento,
        @NotNull
        String cpf,
        @NotNull
        String rg,
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate admissao,
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate demissao,
        String mae,
        String pai,
        @NotNull
        String genero,
        @NotNull
        String estado_civil,
        String naturalidade,
        @NotNull
        String clt,
        @NotNull
        UUID cargo,
        @NotNull
        UUID departamento,
        @NotNull
        UUID centro) {

}
