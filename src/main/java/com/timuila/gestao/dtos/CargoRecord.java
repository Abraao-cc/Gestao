package com.timuila.gestao.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.UUID;
import org.springframework.format.annotation.NumberFormat;

/**
 *
 * @author Administrativo
 */
public record CargoRecord(
        UUID id,
        @NotNull
        @Size(max = 255)
        String nome,
        @NumberFormat(pattern = "#,##0.00", style = NumberFormat.Style.CURRENCY)
        BigDecimal salario) {

}
