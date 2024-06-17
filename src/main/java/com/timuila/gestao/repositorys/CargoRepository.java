package com.timuila.gestao.repositorys;

import com.timuila.gestao.dominio.Cargo;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrativo
 */
@Repository
public interface CargoRepository extends JpaRepository<Cargo, UUID> {

    @Query("SELECT c FROM Cargo c  where c.nome like :search%")
    Page<Cargo> searchAll(String search, Pageable pageable);

    Optional<Cargo> findByNome(String nome);
}
