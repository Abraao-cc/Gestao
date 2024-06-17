
package com.timuila.gestao.repositorys;

import com.timuila.gestao.dominio.Departamento;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrativo
 */
@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento,UUID> {
    
}
