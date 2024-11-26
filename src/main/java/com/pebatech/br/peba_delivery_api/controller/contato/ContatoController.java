package com.pebatech.br.peba_delivery_api.controller.contato;

import com.pebatech.br.peba_delivery_api.domain.dto.contato.ContatoDto;
import com.pebatech.br.peba_delivery_api.domain.form.contato.ContatoForm;
import com.pebatech.br.peba_delivery_api.service.contato.IContatoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@Tag(name = "Contato")
@RequestMapping("/contato")
public class ContatoController {
    @Autowired
    private IContatoService service;

    @Operation(summary = "Busca Contatos")
    @GetMapping
    public ResponseEntity<Page<ContatoDto>> listar(
            @ParameterObject
            @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable paginacao){
        return ResponseEntity.ok().body(service.listar(paginacao));
    }

    @Operation(summary = "Cadastra Contato")
    @PostMapping
    public ResponseEntity<ContatoDto> cadastrar(@RequestBody @Valid ContatoForm form,
                                               UriComponentsBuilder uriBuilder) {
        ContatoDto dto = service.cadastrar(form);
        URI uri = uriBuilder.path("/Contato/{id}").buildAndExpand(dto.getUuid()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @Operation(summary = "Atualiza Contato")
    @PutMapping(value = "/{idContato}")
    public ResponseEntity<ContatoDto> atualizar(@PathVariable UUID idContato, @RequestBody @Valid ContatoForm form) {
        return ResponseEntity.ok(service.atualizar(idContato, form));
    }

    @Operation(summary = "Desativa Contato")
    @DeleteMapping(value = "/{idContato}")
    public ResponseEntity<Void> excluir(@PathVariable UUID idContato) {
        service.excluir(idContato);
        return ResponseEntity.noContent().build();
    }
}