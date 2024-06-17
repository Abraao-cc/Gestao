package com.timuila.gestao.services;

import com.timuila.gestao.dominio.CentroCusto;
import com.timuila.gestao.dominio.Endereco;
import com.timuila.gestao.dtos.CentroCustoRecord;
import com.timuila.gestao.dtos.EnderecoRecord;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author ti
 */
public interface EnderecoService {

    EnderecoRecord salvar(EnderecoRecord enderecoDTO);

    void delete(UUID id);

    Map<String, Object> buscarTodos(HttpServletRequest request);

    List<EnderecoRecord> list(Pageable pageable);

    EnderecoRecord buscarEnderecoPorId(UUID id);

    EnderecoRecord criar(UUID pessoa_id);

    EnderecoRecord buscarPorCep(String cep);

    boolean pessoaExists(final UUID id);

}
