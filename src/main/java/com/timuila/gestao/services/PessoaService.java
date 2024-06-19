package com.timuila.gestao.services;

import com.timuila.gestao.emuns.TipoPessoa;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Administrativo
 */
public interface PessoaService {

    TipoPessoa save(TipoPessoa tipo);

    TipoPessoa buscarPessoaPorId(UUID id);

    List<TipoPessoa> list();
    // List<PessoaJuridicaResponseDTO> list();

    void delete(final UUID id);

}
