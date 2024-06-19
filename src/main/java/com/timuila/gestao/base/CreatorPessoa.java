package com.timuila.gestao.base;

import com.timuila.gestao.emuns.TipoPessoa;
import com.timuila.gestao.services.PessoaService;

/**
 *
 * @author Administrativo
 */
public class CreatorPessoa extends Creator {

    @Override
    public PessoaService tipo(TipoPessoa tipo) {
        PessoaService pessoa = null;
        switch (tipo) {
            case FISICA ->
                pessoa = new Fisica();
                 case JURIDICA ->
                pessoa = new Juridica();
            default ->
                throw new AssertionError();
        }
        return pessoa;
    }

}
