package com.pebatech.br.peba_delivery_api.controller.produto;

import com.pebatech.br.peba_delivery_api.domain.dto.produto.ProdutoDto;
import com.pebatech.br.peba_delivery_api.domain.form.produto.ProdutoForm;
import com.pebatech.br.peba_delivery_api.service.produto.IProdutoService;
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
@Tag(name = "Produto")
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private IProdutoService service;

    @Operation(summary = "Busca produtos")
    @GetMapping
    public ResponseEntity<Page<ProdutoDto>> listar(
            @ParameterObject
            @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable paginacao) {
        return ResponseEntity.ok().body(service.listar(paginacao));
    }

    @Operation(summary = "Cadastra produto")
    @PostMapping
    public ResponseEntity<ProdutoDto> cadastrar(@RequestBody @Valid ProdutoForm form,
                                                UriComponentsBuilder uriBuilder) {
        ProdutoDto dto = service.cadastrar(form);
        URI uri = uriBuilder.path("/produto/{id}").buildAndExpand(dto.getUuid()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @Operation(summary = "Atualiza produto")
    @PutMapping(value = "/{idProduto}")
    public ResponseEntity<ProdutoDto> atualizar(@PathVariable UUID idProduto,
                                                @RequestBody @Valid ProdutoForm form) {
        return ResponseEntity.ok(service.atualizar(idProduto, form));
    }

    @Operation(summary = "Remove produto")
    @DeleteMapping(value = "/{idProduto}")
    public ResponseEntity<Void> excluir(@PathVariable UUID idProduto) {
        service.excluir(idProduto);
        return ResponseEntity.noContent().build();
    }
}
