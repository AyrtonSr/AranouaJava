package edu.aranoua.aula_spring.dto;

import edu.aranoua.aula_spring.model.Cidade;
import edu.aranoua.aula_spring.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CidadeInputDTO {

    private String nome;
    private String estado;


    public CidadeInputDTO() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Cidade build(EstadoRepository estadoRepository){
        Cidade cidade = new Cidade();
        cidade.setNome(this.nome);
        cidade.setEstado(estadoRepository.findByNome(this.estado));
        return cidade;
    }
}
