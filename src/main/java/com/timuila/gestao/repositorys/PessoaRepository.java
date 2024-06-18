package com.timuila.gestao.repositorys;

import com.timuila.gestao.dominio.Pessoa;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrativo
 */
@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, UUID> {

    Optional<Pessoa> findByNome(String nome);
}
