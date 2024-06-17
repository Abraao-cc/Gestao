package com.timuila.gestao.exceptions;

/**
 *
 * @author abraao
 */
public class CargoException extends RuntimeException {

    private final String nome;

    public CargoException(String nome, String message) {
        super(message);
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

   

}
