package com.timuila.gestao.repositorys;

import com.timuila.gestao.domain.Funcionario;
import com.timuila.gestao.domain.Presenca;
import java.time.LocalTime;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrativo
 */
@Repository
public interface PresencaRepository extends JpaRepository<Presenca, UUID> {

    @Query("select  f from Presenca f join f.funcionario fu where fu.nome like :search%")
    Page<Presenca> searchAll(String search, Pageable pageable);

    @Query("select f from Presenca f where f.entrada =:entrada")
    Optional<Presenca> findByEntrada(LocalTime entrada);

    @Query("select f from Presenca f where f.intervalo =:intervalo")
    Optional<Presenca> findByIntervalo(LocalTime intervalo);

    @Query("select f from Presenca f where f.retorno =:retorno")
    Optional<Presenca> findByRetorno(LocalTime retorno);

    @Query("select f from Presenca f where f.saida =:saida")
    Optional<Presenca> findBySaida(LocalTime saida);

    @Query("select f from Presenca f where f.funcionario.id =:funcionario_id")
    Optional<Presenca> findByFuncionarioId(@Param("funcionario_id") UUID funcionario_id);

    Optional<Presenca> findFirstByFuncionario(Funcionario funcionario);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query("update Presenca f set f.entrada = ?1 where f.id= ?2 ")
    void updateEntrada(LocalTime entrada, UUID id);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query("update Presenca f set f.intervalo = ?1 where f.id= ?2 ")
    void updateIntervalo(LocalTime intervalo, UUID id);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query("update Presenca f set f.retorno = ?1 where f.id= ?2 ")
    void updateRetorno(LocalTime retorno, UUID id);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query("update Presenca f set f.saida = ?1 where f.id= ?2 ")
    void updateSaida(LocalTime saida, UUID id);

}
