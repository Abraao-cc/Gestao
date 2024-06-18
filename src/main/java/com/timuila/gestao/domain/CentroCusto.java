package com.timuila.gestao.dominio;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.timuila.gestao.base.Entidade;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 *
 * @author Administrativo
 */
@Entity
@Table(name = "tbl_centro_custos")
public class CentroCusto extends Entidade {

    @Column(length = 80, unique = true)
    private String nome;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "centro", fetch = FetchType.LAZY)
    private Set<Funcionario> funcionarios = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private PessoaJuridica empresa;

    public CentroCusto() {

    }

    public CentroCusto(UUID id) {
        super.setId(id);

    }

    public CentroCusto(String nome) {
        this.nome = nome;

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(Set<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public PessoaJuridica getEmpresa() {
        return empresa;
    }

    public void setEmpresa(PessoaJuridica empresa) {
        this.empresa = empresa;
    }

}
