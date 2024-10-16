package edu.aranoua.aula_spring.model;

import jakarta.persistence.*;
import org.springframework.web.bind.annotation.Mapping;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "pais")
public class Pais {
    @Id
    @Column(name = "pacodigo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "panome",nullable = false,unique = true,length = 100)
    private String nome;
    @Column(name = "pasigla",nullable = false,unique = true,length = 100)
    private String sigla;
    @OneToMany(mappedBy = "pais")
    private List<Estado> estados = new ArrayList<Estado>();

    public Pais(){}
    public Pais(Long id, String nome, String sigla){
        this.id = id;
        this.nome = nome;
        this.sigla = sigla;
    }
    public Pais(String nome, String sigla){
        this.nome = nome;
        this.sigla = sigla;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public List<Estado> getEstados() {
        return estados;
    }

    public void setEstados(List<Estado> estados) {
        this.estados = estados;
    }

    public void addEstado(Estado estado){
        if(!this.estados.contains(estado)){
            this.estados.add(estado);
            estado.setPais(this);
        }
    }
}
