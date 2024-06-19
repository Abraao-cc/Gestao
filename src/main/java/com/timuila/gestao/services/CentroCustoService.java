package com.timuila.gestao.services;

import com.timuila.gestao.domain.CentroCusto;
import com.timuila.gestao.dtos.CentroCustoDTO;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 *
 * @author ti
 */
public interface CentroCustoService {

    CentroCusto save(CentroCustoDTO centroCustoDTO);

    List<CentroCusto> getCentroCustos();

    CentroCustoDTO get(UUID id);

    void delete(UUID id);

    boolean nomeExists(String nome);

    Map<String, Object> buscarTodos(HttpServletRequest request);

    CentroCusto findByNome(String centro);

}
