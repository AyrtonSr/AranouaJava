package edu.aranoua.aula_spring.dto;

import edu.aranoua.aula_spring.model.Estado;
import edu.aranoua.aula_spring.repository.EstadoRepository;

public class EstadoInputDTO {
    private String nome;
    private String sigla;

    public EstadoInputDTO() {
    }

    public String getNome() {
        return nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Estado build(EstadoRepository estadoRepository) {
        Estado estado = new Estado();
        estado.setNome(this.nome);
        estado.setSigla(this.sigla);
        return estado;
    }
}
