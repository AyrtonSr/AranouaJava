package edu.aranoua.aula_spring.controller;

import edu.aranoua.aula_spring.dto.CidadeInputDTO;
import edu.aranoua.aula_spring.dto.CidadeOutputDTO;
import edu.aranoua.aula_spring.model.Cidade;
import edu.aranoua.aula_spring.model.Estado;
import edu.aranoua.aula_spring.repository.CidadeRepository;
import edu.aranoua.aula_spring.repository.EstadoRepository;
import edu.aranoua.aula_spring.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cidade")
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CidadeOutputDTO>> list() {

        List<CidadeOutputDTO> cidadesOutputDTO = cidadeService.list();

        if (!cidadesOutputDTO.isEmpty()) {
            return new ResponseEntity<>(cidadesOutputDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<CidadeOutputDTO>> create(@RequestBody CidadeInputDTO cidadeInputDTO, UriComponentsBuilder uriBuilder) {

        CidadeOutputDTO cidadeOutputDTO = cidadeService.create(cidadeInputDTO);
        if (cidadeOutputDTO != null) {
            UriComponents uriComponents = uriBuilder.path("/api/cidade/{id}").buildAndExpand(cidadeOutputDTO.getId());

            URI uri = uriComponents.toUri();

            //Criar o link para a propria cidade
            /*
            Link selfLink = WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(CidadeController.class).getById(cidadeOutputDTO.getId()).withSelfRel();
            )

             */


            Link allCidadesLink = WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(CidadeController.class).list()).withRel("all-cidades");
            /*
            Link deleteLink = WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(CidadeController.class).list().delete(cidadeOutputDTO.getId()).withRel("delete-cidade");
            )

             */
            EntityModel<CidadeOutputDTO> resource = EntityModel.of(cidadeOutputDTO,allCidadesLink);

            return ResponseEntity.created(uri).contentType(MediaType.APPLICATION_JSON).body(resource);
            //return new ResponseEntity<>(cidadeOutputDTO, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
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
