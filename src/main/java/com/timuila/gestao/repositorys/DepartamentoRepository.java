package com.timuila.gestao.repositorys;

import com.timuila.gestao.dominio.Departamento;
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
public interface DepartamentoRepository extends JpaRepository<Departamento, UUID> {

    @Query("SELECT d FROM Departamento d  where d.nome like :search%")
    Page<Departamento> searchAll(String search, Pageable pageable);

    Optional<Departamento> findByNome(String nome);

}
