package com.timuila.gestao.services;

import com.timuila.gestao.domain.Telefone;
import com.timuila.gestao.dtos.TelefoneDTO;
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

    TelefoneDTO criar(UUID pessoa_id, TelefoneDTO telefone);

    Telefone save(TelefoneDTO telefone);

    TelefoneDTO buscarTelefonePorId(UUID id);

    void delete(UUID id);

    Map<String, Object> buscarTodos(HttpServletRequest request);

    TelefoneDTO buscarPorNumero(String numero);

    List<Telefone> getTelefones();

    List<TelefoneDTO> list(Pageable pageable);

}
