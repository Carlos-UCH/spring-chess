package com.xadrez.carlos.xadrez_variante.controllers;
import com.xadrez.carlos.xadrez_variante.variantes.DadosDetalhamentoXadrez;

import java.util.List;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.xadrez.carlos.xadrez_variante.variantes.DadosAtualizar;
import com.xadrez.carlos.xadrez_variante.variantes.DadosCadastrados;
import com.xadrez.carlos.xadrez_variante.variantes.DadosListagemXadrez;
import com.xadrez.carlos.xadrez_variante.variantes.Xadrez;
import com.xadrez.carlos.xadrez_variante.variantes.XadrezRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/controlar")


public class Controllers {

    @Autowired
    private XadrezRepository repository;

    @PostMapping
    @Transactional

    public ResponseEntity<DadosDetalhamentoXadrez> Cadastro (@RequestBody  @Valid DadosCadastrados dados, UriComponentsBuilder uriBuilder){
        var xadrez = new Xadrez(dados);
        repository.save(xadrez);
        var uri = uriBuilder.path("/controlar/{id}").buildAndExpand(xadrez.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoXadrez(xadrez));
    }
    
    @GetMapping
    public ResponseEntity<List<DadosListagemXadrez>> listar (){
        var lista = repository.findAllByAtivoTrue().stream().map(DadosListagemXadrez::new).toList();
        return ResponseEntity.ok(lista);
    }


    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoXadrez> atualizar(@RequestBody  @Valid DadosAtualizar dados){
        var xadrez = repository.getReferenceById(dados.id());
        xadrez.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoXadrez(xadrez));
    }

    @PutMapping("reativar/{id}")
    @Transactional
    public ResponseEntity<Void> Reativar(@PathVariable Long id){
        var xadrez = repository.getReferenceById(id);
        xadrez.reativar();

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/inativar/{id}")
    @Transactional
    public ResponseEntity<Void> inativar(@PathVariable Long id){
        var xadrez = repository.getReferenceById(id);
        xadrez.inativar();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoXadrez> detalhar(@PathVariable Long id){
        var xadrez = repository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalhamentoXadrez(xadrez));
    }


}
