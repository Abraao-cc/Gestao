package com.timuila.gestao.dominio;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Administrativo
 */
@Entity
@Table(name = "tbl_pessoas")
@Inheritance(strategy = InheritanceType.JOINED)
@SuppressWarnings("serial")
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true)
    protected UUID id;
    @Column(nullable = false, unique = true)
    protected String nome;
    @Column(length = 120)
    protected String sobrenome;
    @Column(name = "nasc")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    protected LocalDate nascimento;
    @OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL)
    protected Endereco endereco;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "pessoa", fetch = FetchType.LAZY)
    protected Set<Telefone> telefones = new HashSet<>();

    public Pessoa() {
    }

    public Pessoa(UUID id) {
        this.id = id;
    }

    public Pessoa(String nome) {
        this.nome = nome;
    }

    public Pessoa(UUID id, String nome, String sobrenome, LocalDate nascimento) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.nascimento = nascimento;
    }

    public void addTelefone(Telefone telefone) {
        this.telefones.add(telefone);
        if (telefone.getPessoa() != this) {
            telefone.setPessoa(this);
        }
    }

    /*
    public void addEndereco(Endereco endereco) {
        this.endereco = endereco;
        endereco.setPessoa(this);
    }

    public void removeEndereco(Endereco endereco) {
        if (endereco != null) {
            endereco.setPessoa(null);
        }
        this.endereco = null;
    }
     */
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public Set<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(Set<Telefone> telefones) {
        this.telefones = telefones;
    }

}
