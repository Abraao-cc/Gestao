package com.timuila.gestao.dominio;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.timuila.gestao.base.Entidade;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 *
 * @author Administrativo
 */
@Entity
@Table(name = "tbl_enderecos")
@SuppressWarnings("serial")
public class Endereco extends Entidade {

    @Column(length = 70)
    private String uf;
    @Column(length = 70)
    private String cidade;
    @Column(length = 70)
    private String bairro;
    @Column(length = 70)
    private String rua;
    //@Pattern("\\d{5}-\\d{3})
    @Column(length = 15)
    private String cep;
    @Column(length = 15)
    private String numero;
    @Column(length = 70)
    private String complemento;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToOne
    @JoinColumn(name = "pessoa_id", nullable = false, unique = true)
    private Pessoa pessoa;

    public Endereco() {
    }

    public Endereco(String uf, String cidade, String bairro, String rua, String cep, String numero, String complemento, Pessoa pessoa) {
        this.uf = uf;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.cep = cep;
        this.numero = numero;
        this.complemento = complemento;
        this.pessoa = pessoa;
    }

    public Endereco(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

}
