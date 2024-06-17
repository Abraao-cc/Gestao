package com.timuila.gestao.dtos;

import java.util.Set;
import java.util.UUID;

/**
 *
 * @author Administrativo
 */
public record BookRecord(
        String title,
        UUID publisher,
        Set<UUID> authors,
        String reviewComment) {

}
