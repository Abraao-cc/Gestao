
package com.timuila.gestao.repositorys;

import com.timuila.gestao.domain.Publisher;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrativo
 */
@Repository
public interface PublisherRepository extends JpaRepository<Publisher, UUID>{
    
}
