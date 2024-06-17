
package com.timuila.gestao.services;
import com.timuila.gestao.dominio.Cargo;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 *
 * @author ti
 */
public interface CargoService {
    
    void salvar(Cargo c);

    Map<UUID, String> cargos();

    Cargo findById(UUID id);

    List<Cargo> lista();

    void delete(UUID id);

    Cargo findByNome(String nome);

    Map<String, Object> buscarTodos(HttpServletRequest request);

}
