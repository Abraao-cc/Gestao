package com.timuila.gestao.emuns;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Administrativo
 */
public enum Periodo {
    MANHA("manhã"), TARDE("tarde"), NOITE("noite");
    private final String value;

    private Periodo(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "EC{" + "value=" + value + '}';
    }

    public static Periodo findTipo(String value) {
        if (value == null) {
            return null;
        }
        return switch (value.toLowerCase()) {
            case "manhã" ->
                Periodo.MANHA;
            case "tarde" ->
                Periodo.TARDE;
            case "noite" ->
                Periodo.NOITE;

            default ->
                throw new IllegalArgumentException(" Período invalido " + value);
        };
    }

    public static Set<String> getEstadoCivil() {
        Set<String> periodos = new HashSet<>();
        for (Periodo periodo : Periodo.values()) {
            periodos.add(periodo.getValue());
        }
        return periodos;
    }
}
