
package com.timuila.gestao.repositorys;

import com.timuila.gestao.dominio.Review;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrativo
 */
@Repository
public interface ReviewRepository extends JpaRepository<Review, UUID>{
    
}
