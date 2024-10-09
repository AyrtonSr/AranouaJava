package edu.aranoua.aula_spring.dto;

import edu.aranoua.aula_spring.model.Estado;

public class EstadoOutputDTO {
    private Long id;
    private String nome;
    private String sigla;

    public EstadoOutputDTO(Estado estado){
        this.id = estado.getId();
        this.nome = estado.getNome();
        this.sigla = estado.getSigla();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
}
