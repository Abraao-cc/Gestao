package com.timuila.gestao.base;

import com.timuila.gestao.emuns.TipoPessoa;

/**
 *
 * @author Administrativo
 * @param <T>
 */
public interface AbstractFactory<T> {

    T create(TipoPessoa tipo);

}
