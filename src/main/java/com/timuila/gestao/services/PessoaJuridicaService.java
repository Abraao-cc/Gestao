package com.timuila.gestao.services;

import com.timuila.gestao.dominio.PessoaJuridica;
import com.timuila.gestao.dtos.PessoaJuridicaDTO;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Administrativo
 */
public interface PessoaJuridicaService {

    PessoaJuridica save(PessoaJuridicaDTO pessoaJuridica);

    PessoaJuridica buscarPessoaJuridicaPorId(UUID id);

    List<PessoaJuridica> list();
   // List<PessoaJuridicaResponseDTO> list();

    void delete(final UUID id);

    // long countById();
    //Map<UUID, String> getPessoaSJudiricas();
    //Map<String, Object> buscarTodos(HttpServletRequest request);
    //Set<PessoaJuridicaDTO> pessoaJuridicaString(Set<String> funcionarosString);
    //PessoaJuridicaDTO buscarPessoaJuridicaPorNome(String nome);
}
