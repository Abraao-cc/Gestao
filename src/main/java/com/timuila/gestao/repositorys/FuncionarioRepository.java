package com.timuila.gestao.repositorys;

import com.timuila.gestao.dominio.Funcionario;
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
public interface FuncionarioRepository extends JpaRepository<Funcionario, UUID> {

    @Query(value = "SELECT distinct f FROM Funcionario f join  f.departamento as d join  f.centro as e join  f.cargo as c",
            countQuery = "SELECT COUNT(obj) FROM Funcionario obj  where obj.clt like :search%")
    Page<Funcionario> searchAll(String search, Pageable pageable);

    //@Query(value = "SELECT distinct f FROM Funcionario f  join  f.departamento as d join  f.empresa as e join  f.cargo as c where f.clt like :search%")
    // Page<FuncionarioBaseProjection> funcionarios(String search, Pageable pageable);
    Optional<Funcionario> findByClt(String clt);

    Optional<Funcionario> findByCpf(String cpf);

    Optional<Funcionario> findByRg(String rg);

    Optional<Funcionario> findByNome(String nome);

    /**
     * @Transactional(readOnly = true)
     * @Query("SELECT obj FROM Funcionario obj where obj.nome like :search%")
     * Page<Funcionario> findByCargo(String search, Pageable pageable);
     * Funcionario findFirstByDepartamento(Departamento departamento);
     *
     * Funcionario findFirstByCargo(Cargo cargo);
     *
     * Funcionario findFirstByCentro(CentroCusto centro);
     *
     * boolean existsByCpfIgnoreCase(String cpf);
     *
     * boolean existsByCltIgnoreCase(String clt);
     *
     * @Query("select fum from Funcionario fum join fum.cargo c " + "where
     * fum.cargo.nome like :nome%") Page<HistoricoFuncionario>
     * findHistoricoByFuncionarioNome(String nome, Pageable pageable);
     */
}
