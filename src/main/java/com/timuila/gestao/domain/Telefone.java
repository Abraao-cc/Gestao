package com.timuila.gestao.dominio;

import com.timuila.gestao.base.Entidade;
import com.timuila.gestao.convert.TipoTelefoneConvert;
import com.timuila.gestao.emuns.TipoTelefone;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 *
 * @author Administrativo
 */
@Entity
@Table(name = "tbl_telefones")
@SuppressWarnings("serial")
public class Telefone extends Entidade {

    @Column(length = 20, unique = true)
    //@Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$", message = "Número do Telefone Obrigatório")
    private String numero;

    @Column(name = "tipo", nullable = false, length = 20)
    @Convert(converter = TipoTelefoneConvert.class)
    private TipoTelefone tipo;
    @ManyToOne
    @JoinColumn(name = "pessoa_id", referencedColumnName = "id", nullable = false)
    private Pessoa pessoa;

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public TipoTelefone getTipo() {
        return tipo;
    }

    public void setTipo(TipoTelefone tipo) {
        this.tipo = tipo;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {

        this.pessoa = pessoa;
        if (!pessoa.getTelefones().contains(this)) { // warning this may cause performance issues if you have a large data set since this operation is O(n)
            pessoa.getTelefones().add(this);
        }
        this.pessoa = pessoa;
    }

}
