package com.timuila.gestao.dominio;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.timuila.gestao.base.Entidade;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import org.springframework.format.annotation.NumberFormat;

/**
 *
 * @author Administrativo
 */
@SuppressWarnings("serial")

@Entity
@Table(name = "tbl_cargos")
public class Cargo extends Entidade {

    @Column(length = 80, unique = true, nullable = false)
    private String nome;
    @NumberFormat(pattern = "#,##0.00", style = NumberFormat.Style.CURRENCY)
    private BigDecimal salario;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "cargo", fetch = FetchType.LAZY)
    private Set<Funcionario> funcionarios = new HashSet<>();

    public Cargo() {

    }

    public Cargo(UUID id) {
        super.setId(id);
    }

    public Cargo(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public Set<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(Set<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

}
