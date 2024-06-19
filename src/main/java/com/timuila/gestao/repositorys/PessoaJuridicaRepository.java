package com.timuila.gestao.repositorys;

import com.timuila.gestao.domain.PessoaJuridica;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrativo
 */
@Repository
public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridica, UUID> {

    Optional<PessoaJuridica> findByCnpj(String cnpj);

    Optional<PessoaJuridica> findByIe(String ie);

    Optional<PessoaJuridica> findByIm(String im);
}
