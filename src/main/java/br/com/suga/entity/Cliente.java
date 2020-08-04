package br.com.suga.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "tb_cliente")
public class Cliente implements Serializable {
    private static final long serialVersionUID = 2496550266971392576L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "cpf")
    @CPF
    private String cpf;

    @Column(name = "nome")
    @Length(min = 3, message = "Nome deve ter pelo menos 3 caracteres")
    @NotNull(message = "Nome não pode ser nulo")
    private String nome;

    @Length(min = 3, message = "Sobrenome deve ter pelo menos 3 caracteres")
    @NotNull(message = "Sobrenome não pode ser nulo")
    private String sobrenome;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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
}
