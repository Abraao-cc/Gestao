package com.timuila.gestao.services;

import com.timuila.gestao.domain.Cargo;
import com.timuila.gestao.dtos.CargoDTO;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 *
 * @author ti
 */
public interface CargoService {

    Cargo save(CargoDTO c);

    Map<UUID, String> cargos();

    Cargo findById(UUID id);

    List<Cargo> lista();

    void delete(UUID id);

    Cargo findByNome(String nome);

    Map<String, Object> buscarTodos(HttpServletRequest request);

}
