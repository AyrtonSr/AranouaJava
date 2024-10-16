package edu.aranoua.aula_spring.dto;

import edu.aranoua.aula_spring.model.Pessoa;
import edu.aranoua.aula_spring.repository.CidadeRepository;

public class PessoaInputDTO {
    private String cpf;
    private String nome;
    private int idade;
    private String cidade;

    public PessoaInputDTO(){}

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public int getIdade() {
        return idade;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }


    public Pessoa build(CidadeRepository cidadeRepository){
        Pessoa pessoa = new Pessoa();
        pessoa.setCpf(this.cpf);
        pessoa.setNome(this.nome);
        pessoa.setIdade(this.idade);
        pessoa.setCidade(cidadeRepository.findByNome(this.cidade));
        return pessoa;
    }
}
