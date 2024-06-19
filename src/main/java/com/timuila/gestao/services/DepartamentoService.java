package com.timuila.gestao.services;

import com.timuila.gestao.domain.Departamento;
import com.timuila.gestao.dtos.DepartamentoDTO;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 *
 * @author Administrativo
 */
public interface DepartamentoService {

    Departamento save(DepartamentoDTO departamentoDTO);

    List<Departamento> getDepartamentos();

    Map<UUID, String> cargos();

    Departamento findById(UUID id);

    void delete(UUID id);

    Departamento findByNome(String nome);

}
