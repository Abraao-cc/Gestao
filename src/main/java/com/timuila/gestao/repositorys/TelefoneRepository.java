
package com.timuila.gestao.repositorys;

import com.timuila.gestao.dominio.Funcionario;
import com.timuila.gestao.dominio.Telefone;
import com.timuila.gestao.emuns.TipoTelefone;
import com.timuila.gestao.projections.TelefoneProjecyion;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrativo
 */
@Repository
public interface TelefoneRepository extends JpaRepository<Telefone,UUID> {
     @Query("select t from Telefone t where t.numero = :numero")
    Optional<Telefone> findByNumero(String numero);

    @Query("select t from Telefone t join t.pessoa p where p.id  =:pessoa_id")
    Optional<Telefone> findByPessoa(UUID pessoa_id);

    @Transactional
    void deleteByPessoaId(UUID pessoaId);

    @Query("select t from Telefone t where  t.pessoa.id  =:pessoa_id")
    Optional<Telefone> findByPessoaId(UUID pessoa_id);

    @Query("select t from Telefone t where  t.pessoa.id =:pessoa_id")
    Page<Telefone> findByIdPessoa(UUID pessoa_id, Pageable pageable);

    @Query("select t.numero from Telefone t JOIN t.pessoa p where  p.id  =:pessoa_id")
    Page<Telefone> findPessoa(UUID pessoa_id, Pageable pageable);

    @Query("select t from Telefone t  where t.pessoa.id =:pessoa_id")
    List<Telefone> findTelefonesByPessoaId(UUID pessoa_id);

    @Query("select t from Telefone t join fetch t.pessoa p where p.id  =:pessoa_id")
    Telefone findTelefone(UUID pessoa_id);

    @Query(value = "SELECT obj FROM Telefone obj JOIN  obj.pessoa p ")
    List<Telefone> searchAll();

    @Query(value = "SELECT obj FROM Telefone obj JOIN   obj.pessoa",
            countQuery = "SELECT COUNT(obj) FROM Telefone obj  JOIN obj.pessoa")
    Page<Telefone> searchAll(Pageable pageable);

    @Query(value = "SELECT obj FROM Telefone obj left join  obj.pessoa p where  obj.tipo like :tipo%")
    Page<Telefone> findAllByTelefone(TipoTelefone tipo, Pageable pageable);

    @Query(value = "SELECT t.id as id, t.numero as numero, t.tipo as tipo, t.pessoa.nome as nome FROM Telefone t  where  t.tipo like :tipo%")
    Page<TelefoneProjecyion> telefones(TipoTelefone tipo, Pageable pageable);

    Telefone findFirstByPessoa(Funcionario funcionario);
}
