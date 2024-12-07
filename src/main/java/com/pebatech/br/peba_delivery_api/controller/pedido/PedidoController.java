package com.pebatech.br.peba_delivery_api.controller.pedido;

import com.pebatech.br.peba_delivery_api.domain.dto.pedido.PedidoDto;
import com.pebatech.br.peba_delivery_api.domain.form.pedido.PedidoForm;
import com.pebatech.br.peba_delivery_api.service.pedido.IPedidoService;
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
@Tag(name = "Pedido")
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private IPedidoService service;

    @Operation(summary = "Busca pedidos")
    @GetMapping
    public ResponseEntity<Page<PedidoDto>> listar(
            @ParameterObject
            @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable paginacao) {
        return ResponseEntity.ok().body(service.listar(paginacao));
    }

    @Operation(summary = "Cadastra pedido")
    @PostMapping
    public ResponseEntity<PedidoDto> cadastrar(@RequestBody @Valid PedidoForm form,
                                               UriComponentsBuilder uriBuilder) {
        PedidoDto dto = service.cadastrar(form);
        URI uri = uriBuilder.path("/pedido/{id}").buildAndExpand(dto.getUuid()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @Operation(summary = "Atualiza pedido")
    @PutMapping(value = "/{idPedido}")
    public ResponseEntity<PedidoDto> atualizar(@PathVariable UUID idPedido,
                                               @RequestBody @Valid PedidoForm form) {
        return ResponseEntity.ok(service.atualizar(idPedido, form));
    }

    @Operation(summary = "Remove pedido")
    @DeleteMapping(value = "/{idPedido}")
    public ResponseEntity<Void> excluir(@PathVariable UUID idPedido) {
        service.excluir(idPedido);
        return ResponseEntity.noContent().build();
    }
}

