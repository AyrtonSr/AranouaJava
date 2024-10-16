package edu.aranoua.aula_spring.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity(name = "estado")
public class Estado {
    @Id
    @Column(name = "estadocodigo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "estadonome",nullable = false,unique = true)
    private String nome;
    @Column(name = "estadosigla",nullable = false,unique = true)
    private String sigla;
    @OneToMany(mappedBy = "estado")
    private List<Cidade> cidades = new ArrayList<Cidade>();
    @ManyToOne
    private Pais pais;

    public Estado() {
    }

    public Estado(Long id, String nome, String sigla) {
        this.id = id;
        this.nome = nome;
        this.sigla = sigla;
    }
    public Estado(String nome, String sigla) {
        this.nome = nome;
        this.sigla = sigla;
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

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public List<Cidade> getCidades() {
        return cidades;
    }

    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public void addCidade(Cidade cidade){
        if(!this.cidades.contains(cidade)){
            this.cidades.add(cidade);
            cidade.setEstado(this);
        }
    }

    @Override
    public String toString() {
        return "Estado{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", sigla='" + sigla + '\'' +
                '}';
    }

}
