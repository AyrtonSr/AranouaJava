package edu.aranoua.aula_spring.controller;

import edu.aranoua.aula_spring.dto.PessoaInputDTO;
import edu.aranoua.aula_spring.dto.PessoaOutputDTO;
import edu.aranoua.aula_spring.model.Pessoa;
import edu.aranoua.aula_spring.repository.CidadeRepository;
import edu.aranoua.aula_spring.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pessoa")
public class PessoaController {
    @Autowired
    PessoaRepository pessoaRepository;
    @Autowired
    CidadeRepository cidadeRepository;
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PessoaOutputDTO>>list(){
        List<Pessoa> pessoas = pessoaRepository.findAll();
        List<PessoaOutputDTO> pessoasOutputDTO = new ArrayList<>();

        for(Pessoa pessoa:pessoas){
            pessoasOutputDTO.add(new PessoaOutputDTO(pessoa));
        }

        if(!pessoasOutputDTO.isEmpty()){
            return new ResponseEntity<>(pessoasOutputDTO, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PessoaOutputDTO> create(@RequestBody PessoaInputDTO pessoaInputDTO){
        try{
           Pessoa pessoa = pessoaInputDTO.build(cidadeRepository);
           Pessoa pessoaNoBD = pessoaRepository.save(pessoa);
           PessoaOutputDTO pessoaOutputDTO = new PessoaOutputDTO(pessoaNoBD);
           return new ResponseEntity<>(pessoaOutputDTO,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PessoaOutputDTO> update(@PathVariable Long id, @RequestBody PessoaInputDTO pessoaInputDTO){
        try{
            Optional<Pessoa> possivelPessoa = pessoaRepository.findById(id);
            if(possivelPessoa.isPresent()){
                Pessoa pessoaEncontrada = possivelPessoa.get();
                pessoaEncontrada.setNome(pessoaInputDTO.getNome());
                pessoaEncontrada.setCpf(pessoaInputDTO.getCpf());
                pessoaEncontrada.setIdade(pessoaInputDTO.getIdade());
                pessoaEncontrada.setCidade(cidadeRepository.findByNome(pessoaInputDTO.getCidade()));

                Pessoa pessoaAtualizada = pessoaRepository.save(pessoaEncontrada);
                PessoaOutputDTO pessoaOutputDTO = new PessoaOutputDTO(pessoaAtualizada);

                return new ResponseEntity<>(pessoaOutputDTO, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
