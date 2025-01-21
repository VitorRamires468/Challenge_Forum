package br.com.alura.forum.controller;

import br.com.alura.forum.domain.topico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("topicos")
public class TopicoController {

    @Autowired
    private TopicoService service;

    @Autowired
    private TopicoRepository repository;

    @PostMapping
    @Transactional
    private ResponseEntity cadastrar(@RequestBody @Valid DadosCadastramentoTopico dados, UriComponentsBuilder uriComponentsBuilder){
        var dto = service.cadastrarTopico(dados);
        var uri = uriComponentsBuilder.path("medicos/{id}").buildAndExpand(dto.id()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping
    private ResponseEntity<Page<DadosListagemTopico>> listar(@PageableDefault(size = 10, sort = {"data"}) Pageable paginacao){
        var page = repository.findAll(paginacao).map( d -> new DadosListagemTopico(d.getTitulo(),d.getMensagem(),d.getData(),d.getStatus().toString(),d.getAutor().getNome(),d.getCurso()));
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    private ResponseEntity detalhamentoTopico(@PathVariable Long id){
        var topico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico.getId(), topico.getTitulo(), topico.getMensagem(),topico.getData(), topico.getCurso(), topico.getStatus().toString(), topico.getAutor().getNome()));
    }

    @PutMapping("/{id}")
    @Transactional
    private ResponseEntity atualizar(@PathVariable Long id, @RequestBody @Valid DadosCadastramentoTopico dados){
        var dto = service.atualizarInformacoes(id, dados);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    @Transactional
    private ResponseEntity deletar(@PathVariable Long id){
        service.deletarTopico(id);
        return ResponseEntity.noContent().build();
    }

}

