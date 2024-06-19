package com.timuila.gestao.base;

import com.timuila.gestao.emuns.TipoPessoa;
import com.timuila.gestao.services.PessoaService;

/**
 *
 * @author Administrativo
 */
public abstract class Creator {

    public abstract PessoaService tipo(TipoPessoa tipo);

    public String exibeMensagem() {
        return "Bem-vindo à classe pessoas";
    }
}
