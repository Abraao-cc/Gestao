package com.timuila.gestao.projections;
import com.timuila.gestao.domain.Pessoa;

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
