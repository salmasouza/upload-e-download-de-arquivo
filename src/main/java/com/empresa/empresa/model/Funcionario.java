package com.empresa.empresa.model;

import javax.persistence.*;
import java.math.BigDecimal;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tbl_funcionario_pessoa")
public class Funcionario extends Pessoa {

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "funcionario_funcao")
    private String funcao;

    @Column(name = "funcionario_salario")
    private BigDecimal salario;

    public Funcionario (){};
    public Funcionario( Long id, String funcao, BigDecimal salario, String nome, LocalDate dataNascimento){
        super(nome, dataNascimento);
        this.id = id;
        this.funcao = funcao;
        this.salario = salario;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }
    public BigDecimal getSalario() {
        DecimalFormat df = new DecimalFormat("#,###.00");

        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getSalarioFormatado() {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        return decimalFormat.format(salario);
    }

    @Override
    public LocalDate getDataNascimento() {
        return super.getDataNascimento();
    }

    @Override
    public String getNome() {
        return super.getNome();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Funcionario funcionario = (Funcionario) o;
        return Objects.equals(id, funcionario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "id=" + id +
                ", funcao='" + funcao + '\'' +
                ", salario=" + salario +
                '}';
    }
}
