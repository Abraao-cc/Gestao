package com.timuila.gestao.services;

import com.timuila.gestao.domain.Endereco;
import com.timuila.gestao.dtos.EnderecoDTO;
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

    Endereco save(EnderecoDTO enderecoDTO);

    void delete(UUID id);

    Map<String, Object> buscarTodos(HttpServletRequest request);

    List<Endereco> getEnderecos();

    List<EnderecoDTO> list(Pageable pageable);

    EnderecoDTO buscarEnderecoPorId(UUID id);

    EnderecoDTO criar(UUID pessoa_id);

    EnderecoDTO buscarPorCep(String cep);

    boolean pessoaExists(final UUID id);

}
