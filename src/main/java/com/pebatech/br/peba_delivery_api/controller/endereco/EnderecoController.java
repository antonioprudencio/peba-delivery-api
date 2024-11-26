package com.pebatech.br.peba_delivery_api.controller.endereco;

import com.pebatech.br.peba_delivery_api.domain.dto.endereco.EnderecoDto;
import com.pebatech.br.peba_delivery_api.domain.form.endereco.EnderecoForm;
import com.pebatech.br.peba_delivery_api.service.endereco.IEnderecoService;
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
@Tag(name = "Endereco")
@RequestMapping("/endereco")
public class EnderecoController {
    @Autowired
    private IEnderecoService service;

    @Operation(summary = "Busca Endereços")
    @GetMapping
    public ResponseEntity<Page<EnderecoDto>> listar(
            @ParameterObject
            @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable paginacao){
        return ResponseEntity.ok().body(service.listar(paginacao));
    }

    @Operation(summary = "Cadastra Endereco")
    @PostMapping
    public ResponseEntity<EnderecoDto> cadastrar(@RequestBody @Valid EnderecoForm form,
                                               UriComponentsBuilder uriBuilder) {
        EnderecoDto dto = service.cadastrar(form);
        URI uri = uriBuilder.path("/Endereco/{id}").buildAndExpand(dto.getUuid()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @Operation(summary = "Atualiza Endereco")
    @PutMapping(value = "/{idEndereco}")
    public ResponseEntity<EnderecoDto> atualizar(@PathVariable UUID idEndereco, @RequestBody @Valid EnderecoForm form) {
        return ResponseEntity.ok(service.atualizar(idEndereco, form));
    }

    @Operation(summary = "Desativa Endereco")
    @DeleteMapping(value = "/{idEndereco}")
    public ResponseEntity<Void> excluir(@PathVariable UUID idEndereco) {
        service.excluir(idEndereco);
        return ResponseEntity.noContent().build();
    }
}