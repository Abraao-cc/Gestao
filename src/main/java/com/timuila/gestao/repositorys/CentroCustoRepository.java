package com.timuila.gestao.repositorys;

import com.timuila.gestao.dominio.CentroCusto;
import java.util.List;
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
public interface CentroCustoRepository extends JpaRepository<CentroCusto, UUID> {
    
    @Query("SELECT c FROM CentroCusto c ")
    List<CentroCusto> searchAll();

    @Query("SELECT c from CentroCusto c  where c.nome like :search%")
    Page<CentroCusto> searchAll(String search, Pageable pageable);

    Optional<CentroCusto> findByNome(String contrato);

    List<CentroCusto> findAllByOrderByNomeAscIdDesc();

     boolean existsByNomeIgnoreCase(String nome);

}
