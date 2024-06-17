package com.timuila.gestao.projections;
import com.timuila.gestao.dominio.Pessoa;

/**
 *
 * @author ti
 */
public interface TelefoneProjecyion {

    Long getId();

    String getNumero();

    String getTipo();

    Pessoa getPessoa();

}
