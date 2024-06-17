package com.timuila.gestao.repositorys;

import com.timuila.gestao.dominio.Endereco;
import com.timuila.gestao.dominio.Pessoa;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrativo
 */
@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, UUID> {

    @Query("select e from Endereco e where e.cep =:cep")
    Optional<Endereco> findByCep(String cep);

    @Query("select e from Endereco e where e.pessoa.id =:pessoa_id")
    Optional<Endereco> findByPessoaId(@Param("pessoa_id") UUID pessoa_id);

    @Query("select e from Endereco e where e.pessoa.nome =:nome")
    Optional<Endereco> findByPessoaNome(@Param("nome") String nome);

    @Query(value = "SELECT obj FROM Endereco obj JOIN  obj.pessoa")
    List<Endereco> searchAll();

    @Query(value = "SELECT obj FROM Endereco obj  JOIN  obj.pessoa",
            countQuery = "SELECT COUNT(obj) FROM Endereco obj  JOIN obj.pessoa")
    Page<Endereco> searchAll(Pageable pageable);

    @Query(value = "SELECT obj FROM Endereco obj  JOIN  obj.pessoa p where  obj.cep like :cep%")
    Page<Endereco> findAllByEndereco(String cep, Pageable pageable);

    // @Query(value = "SELECT obj FROM Endereco obj  INNER JOIN  obj.pessoa p where  p.nome =:pessoa_nome")
    // Page<EnderecoProjection> findByEnderecoPessoaNome(@Param("pessoa_nome") String pessoa_nome, Pageable pageable);
    Endereco findByPessoa(Pessoa pessoa);

    Endereco findFirstByPessoa(Pessoa pessoa);

    boolean existsByPessoaId(UUID id);
}
