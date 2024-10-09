package edu.aranoua.aula_spring.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "cidade")
public class Cidade {

    @Id
    @Column(name = "cidcodigo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "cidnome",nullable = false,unique = true,length = 100)
    private String nome;
    @ManyToOne
    private Estado estado;

    @OneToMany(mappedBy = "cidade")
    private List<Pessoa> pessoas = new ArrayList<Pessoa>();

    public Cidade() {
    }
    public Cidade(Long id,String nome, Estado estado) {
        this.id = id;
        this.nome = nome;
        this.estado = estado;
    }
    public Cidade(String nome, Estado estado) {
        this.nome = nome;
        this.estado = estado;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    @Override
    public String toString() {
        return "Cidade{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }

    public void addPessoa(Pessoa pessoa){
        if(!this.pessoas.contains(pessoa)) {
            this.pessoas.add(pessoa);
            pessoa.setCidade(this);
        }
    }


}
