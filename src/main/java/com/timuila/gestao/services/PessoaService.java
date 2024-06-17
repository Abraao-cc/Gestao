package com.timuila.gestao.services;

import com.timuila.gestao.dominio.Funcionario;
import com.timuila.gestao.dominio.Pessoa;
import com.timuila.gestao.dtos.FuncionarioRecord;
import com.timuila.gestao.dtos.PessoaRecord;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 *
 * @author Administrativo
 */
public interface PessoaService {

    Pessoa save(PessoaRecord pessoa);
    //Pessoa save(PessoaRecord pessoa);

    Pessoa update(Pessoa pessoa);

    void delete(UUID id);

    List<Pessoa> getPessoas();

    Pessoa buscarPessoaPorId(UUID id);

    Pessoa buscarPessoaPorNome(String nome);

    Map<String, Object> buscarTodos(HttpServletRequest request);

    long countById();
}
