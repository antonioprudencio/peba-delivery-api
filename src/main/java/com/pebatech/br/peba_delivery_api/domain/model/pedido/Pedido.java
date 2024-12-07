package com.pebatech.br.peba_delivery_api.domain.model.pedido;


import com.pebatech.br.peba_delivery_api.domain.dto.pedido.PedidoDto;
import com.pebatech.br.peba_delivery_api.domain.enums.StatusPedidoEnum;
import com.pebatech.br.peba_delivery_api.domain.model.ModeloGenerico;
import com.pebatech.br.peba_delivery_api.domain.model.pedido.item_pedido.ItemPedido;
import com.pebatech.br.peba_delivery_api.domain.model.pessoa.Pessoa;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter
@Table(name = "tb_pedido")
public class Pedido extends ModeloGenerico {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusPedidoEnum status;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Pessoa cliente;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens;

    private BigDecimal valorTotal;

    @Column(length = 500)
    private String observacao;

    public PedidoDto toDto() {
        PedidoDto dto = new PedidoDto();
        dto.setUuid(this.getUuid());
        dto.setDataPedido(this.getDataCriacao());
        dto.setStatus(this.status);
        dto.setUuidCliente(this.cliente != null ? this.cliente.getUuid() : null);
        dto.setItens(this.itens.stream()
                .map(ItemPedido::toDto)
                .collect(Collectors.toList()));
        dto.setValorTotal(this.valorTotal);
        dto.setObservacao(this.observacao);
        return dto;
    }
}

