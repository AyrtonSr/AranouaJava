package edu.aranoua.aula_spring.service;

import edu.aranoua.aula_spring.dto.CidadeInputDTO;
import edu.aranoua.aula_spring.dto.CidadeOutputDTO;
import edu.aranoua.aula_spring.model.Cidade;
import edu.aranoua.aula_spring.repository.CidadeRepository;
import edu.aranoua.aula_spring.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CidadeService {
    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    public List<CidadeOutputDTO> list(){

        List<Cidade> cidades = cidadeRepository.findAll();
        List<CidadeOutputDTO> cidadesOutputDTO = new ArrayList<>();

        for(Cidade cidade: cidades){
            cidadesOutputDTO.add(new CidadeOutputDTO(cidade));
        }
        return cidadesOutputDTO;
    }

    public CidadeOutputDTO create(CidadeInputDTO cidadeInputDTO){
        try {
            Cidade cidade = cidadeInputDTO.build(estadoRepository);

            Cidade cidadeNoBD = cidadeRepository.save(cidade);

            CidadeOutputDTO cidadeOutputDTO = new CidadeOutputDTO(cidadeNoBD);

            return cidadeOutputDTO;
        }catch(Exception e){
            return null;
        }
    }

    /*
    @PutMapping(value ="/{id}" ,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CidadeOutputDTO> update(@PathVariable Long id, @RequestBody CidadeInputDTO cidadeInputDTO){

        try {

            Optional<Cidade> possivelCidade = cidadeRepository.findById(id);

            if (possivelCidade.isPresent()) {

                Cidade cidadeEncontrada = possivelCidade.get();

                cidadeEncontrada.setNome(cidadeInputDTO.getNome());
                cidadeEncontrada.setEstado(estadoRepository.findByNome(cidadeInputDTO.getEstado()));

                Cidade cidadeAtualizada = cidadeRepository.save(cidadeEncontrada);

                CidadeOutputDTO cidadeOutputDTO = new CidadeOutputDTO(cidadeAtualizada);

                return new ResponseEntity<>(cidadeOutputDTO, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    */
}
