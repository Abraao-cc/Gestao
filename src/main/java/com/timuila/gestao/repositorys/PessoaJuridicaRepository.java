package com.timuila.gestao.repositorys;

import com.timuila.gestao.dominio.PessoaJuridica;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrativo
 */
@Repository
public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridica, UUID> {

}
