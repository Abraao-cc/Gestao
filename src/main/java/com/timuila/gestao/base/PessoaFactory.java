package com.timuila.gestao.base;

import com.timuila.gestao.emuns.TipoPessoa;
import static com.timuila.gestao.emuns.TipoPessoa.FISICA;
import static com.timuila.gestao.emuns.TipoPessoa.JURIDICA;
import java.util.HashMap;

/**
 *
 * @author Administrativo
 */
public class PessoaFactory implements AbstractFactory<Ppessoa> {

    HashMap<String, Ppessoa> prototiposdePessoas = new HashMap<>();

    public PessoaFactory() {
        prototiposdePessoas.put(TipoPessoa.FISICA.name(), new Fisica());
        prototiposdePessoas.put(TipoPessoa.JURIDICA.name(), new Juridica());

    }

    @Override
    public Ppessoa create(TipoPessoa tipo) {
        return prototiposdePessoas.get(tipo.name()).clone();
    }

}
