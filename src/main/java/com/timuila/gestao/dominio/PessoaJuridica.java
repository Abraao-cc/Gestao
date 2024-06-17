package com.timuila.gestao.dominio;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 *
 * @author Administrativo
 */
@Entity
@Table(name = "tbl_pessoas_juridicas")
@PrimaryKeyJoinColumn(name = "id")
public class PessoaJuridica extends Pessoa {

    @Column(nullable = false, unique = true)
    private String cnpj;
    @Column(nullable = false, unique = true)
    private String ie;
    @Column(nullable = false, unique = true)
    private String im;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "empresa", fetch = FetchType.LAZY)
    private Set<CentroCusto> centroCustos = new HashSet<>();

    public PessoaJuridica() {
    }

    public PessoaJuridica(UUID id) {
        super.setId(id);
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getIe() {
        return ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }

    public String getIm() {
        return im;
    }

    public void setIm(String im) {
        this.im = im;
    }

    public Set<CentroCusto> getCentroCustos() {
        return centroCustos;
    }

    public void setCentroCustos(Set<CentroCusto> centroCustos) {
        this.centroCustos = centroCustos;
    }

}
