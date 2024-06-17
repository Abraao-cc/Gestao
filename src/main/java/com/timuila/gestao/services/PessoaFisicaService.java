package com.timuila.gestao.services;

import com.timuila.gestao.dominio.Funcionario;
import com.timuila.gestao.dtos.FuncionarioRecord;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 *
 * @author Administrativo
 */
public interface PessoaFisicaService {

    Map<UUID, String> funcionarios();

    Map<String, Object> buscarTodos(HttpServletRequest request);

    void delete(final UUID id);

    List<FuncionarioRecord> list();

    Set<Funcionario> funcionarioString(Set<String> funcionarosString);

    FuncionarioRecord buscarFuncionarioPorId(UUID id);

    FuncionarioRecord buscarFuncionarioPorNome(String nome);

    UUID save(FuncionarioRecord funcionario);

    long countById();
}
