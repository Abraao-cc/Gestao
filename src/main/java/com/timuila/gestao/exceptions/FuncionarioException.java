package br.ind.cmil.gestao.exceptions;

/**
 *
 * @author abraao
 */
public class FuncionarioException extends RuntimeException {

    private final String nome;

    public FuncionarioException(String nome, String message) {
        super(message);
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

   

}
