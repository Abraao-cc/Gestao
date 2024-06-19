package com.timuila.gestao.domain;

import com.timuila.gestao.base.Entidade;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.time.LocalDate;
import java.time.LocalTime;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Administrativo
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "tbl_presencas")
public class Presenca extends Entidade {

    @Transient
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime horaAtual;
    @Column(name = "data")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate data;
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime entrada;
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime intervalo;
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime retorno;
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime saida;
    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    public Presenca() {
    }

    public LocalTime getHoraAtual() {
        return horaAtual;
    }

    public void setHoraAtual(LocalTime horaAtual) {
        this.horaAtual = horaAtual;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public LocalTime getEntrada() {
        return entrada;
    }

    public void setEntrada(LocalTime entrada) {
        this.entrada = entrada;
    }

    public LocalTime getIntervalo() {
        return intervalo;
    }

    public void setIntervalo(LocalTime intervalo) {
        this.intervalo = intervalo;
    }

    public LocalTime getRetorno() {
        return retorno;
    }

    public void setRetorno(LocalTime retorno) {
        this.retorno = retorno;
    }

    public LocalTime getSaida() {
        return saida;
    }

    public void setSaida(LocalTime saida) {
        this.saida = saida;
    }

  

}
