package com.pebatech.br.peba_delivery_api.controller.pessoa;

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

import com.pebatech.br.peba_delivery_api.domain.dto.pessoa.PessoaDto;
import com.pebatech.br.peba_delivery_api.domain.form.pessoa.PessoaForm;
import com.pebatech.br.peba_delivery_api.service.pessoa.IPessoaService;

@RestController
@Tag(name = "Pessoa")
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private IPessoaService service;

    @Operation(summary = "Busca pessoas")
    @GetMapping
    public ResponseEntity<Page<PessoaDto>> listar(
            @ParameterObject
            @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable paginacao){
        return ResponseEntity.ok().body(service.listar(paginacao));
    }

    @Operation(summary = "Cadastra pessoa")
    @PostMapping
    public ResponseEntity<PessoaDto> cadastrar(@RequestBody @Valid PessoaForm form,
                                               UriComponentsBuilder uriBuilder) {
        PessoaDto dto = service.cadastrar(form);
        URI uri = uriBuilder.path("/pessoa/{id}").buildAndExpand(dto.getUuid()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @Operation(summary = "Atualiza pessoa")
    @PutMapping(value = "/{idPessoa}")
    public ResponseEntity<PessoaDto> atualizar(@PathVariable UUID idPessoa, @RequestParam("form") @Valid PessoaForm form) {
        return ResponseEntity.ok(service.atualizar(idPessoa, form));
    }

    @Operation(summary = "Desativa pessoa")
    @DeleteMapping(value = "/{idPessoa}")
    public ResponseEntity<Void> excluir(@PathVariable UUID idPessoa) {
        service.excluir(idPessoa);
        return ResponseEntity.noContent().build();
    }
}

