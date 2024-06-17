
package com.timuila.gestao.repositorys;

import com.timuila.gestao.dominio.Autor;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrativo
 */
@Repository
public interface AutorRepository extends JpaRepository<Autor, UUID>{
    
}
