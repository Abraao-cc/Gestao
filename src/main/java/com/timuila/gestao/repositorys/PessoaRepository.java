package com.timuila.gestao.repositorys;

import com.timuila.gestao.domain.Pessoa;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrativo
 */
@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, UUID> {

  
    Pessoa findByNome(String nome);
}
