package com.timuila.gestao.repositorys;

import com.timuila.gestao.dominio.Pessoa;
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
public interface PessoaRepository extends JpaRepository<Pessoa, UUID> {

    Optional<Pessoa> findByNome(String nome);

    Optional<Pessoa> findBySobrenome(String sobronome);

    @Query(value = "SELECT distinct p FROM Pessoa p  where p.nome like :search%")
    Page<Pessoa> pessoas(String search, Pageable pageable);
}
