package com.timuila.gestao.services;

import com.timuila.gestao.domain.Funcionario;
import com.timuila.gestao.dtos.FuncionarioDTO;
import com.timuila.gestao.dtos.FuncionarioResponseDTO;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Administrativo
 */
public interface FuncionarioService {

    Funcionario save(FuncionarioDTO funcionario);

    List<FuncionarioResponseDTO> list();

    Funcionario buscarFuncionarioPorId(UUID id);

    void delete(final UUID id);
    //Map<UUID, String> funcionarios();

    //Map<String, Object> buscarTodos(HttpServletRequest request);
    // Set<Funcionario> funcionarioString(Set<String> funcionarosString);
    //FuncionarioDTO buscarFuncionarioPorNome(String nome);
    // long countById();
}
