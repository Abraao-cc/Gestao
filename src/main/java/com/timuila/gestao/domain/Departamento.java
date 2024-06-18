
package com.timuila.gestao.dominio;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.timuila.gestao.base.Entidade;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Administrativo
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "tbl_departamentos")
public class Departamento extends Entidade {

    @Column(length = 80, unique = true, nullable = false)
    private String nome; 
     @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "departamento", fetch = FetchType.LAZY)
    private Set<Funcionario> funcionarios = new HashSet<>();

    public Departamento() {
    }

    public Departamento(UUID id) {
        super.setId(id);
    }

    public Departamento(String nome) {
        this.nome = nome;
    }

    
}
