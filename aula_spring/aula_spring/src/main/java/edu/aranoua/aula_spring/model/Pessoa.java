package edu.aranoua.aula_spring.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "pessoa")
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,unique = true, length = 11)
    private String cpf;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = true)
    private int idade;
    @ManyToOne
    private Cidade cidade;

    public Pessoa() {
    }

    public Pessoa(Long id,String cpf, String nome, int idade) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.idade = idade;
    }
    public Pessoa(String cpf, String nome, int idade) {
        this.cpf = cpf;
        this.nome = nome;
        this.idade = idade;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
        cidade.addPessoa(this);
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "id=" + id +
                ", cpf='" + cpf + '\'' +
                ", nome='" + nome + '\'' +
                ", idade=" + idade +
                ", cidade=" + cidade +
                '}';
    }
}
