package com.timuila.gestao.services;

import com.timuila.gestao.dominio.CentroCusto;
import com.timuila.gestao.dtos.CentroCustoRecord;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 *
 * @author ti
 */
public interface CentroCustoService {

    List<CentroCustoRecord> findAll();

    CentroCustoRecord get(UUID id);

    UUID create(CentroCustoRecord centroCustoDTO);

    void delete(UUID id);

    boolean nomeExists(String nome);

    Map<String, Object> buscarTodos(HttpServletRequest request);

    CentroCusto findByNome(String centro);

}
