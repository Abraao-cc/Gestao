package com.timuila.gestao.base;

import com.timuila.gestao.emuns.TipoPessoa;

/**
 *
 * @author Administrativo
 */
public interface Ppessoa {

    TipoPessoa tipo();

    String save();

    Ppessoa clone();
}
