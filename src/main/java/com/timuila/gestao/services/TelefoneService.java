package com.timuila.gestao.services;

import com.timuila.gestao.dtos.EnderecoRecord;
import com.timuila.gestao.dtos.TelefoneRecord;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author ti
 */
public interface TelefoneService {

    TelefoneRecord criar(UUID pessoa_id, TelefoneRecord telefone);

    UUID salvar(TelefoneRecord telefone);

    TelefoneRecord buscarTelefonePorId(UUID id);

    void delete(UUID id);

    Map<String, Object> buscarTodos(HttpServletRequest request);

    TelefoneRecord buscarPorNumero(String numero);

    List<TelefoneRecord> list(Pageable pageable);

}
