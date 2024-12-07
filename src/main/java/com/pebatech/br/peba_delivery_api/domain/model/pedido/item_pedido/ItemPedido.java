package com.pebatech.br.peba_delivery_api.domain.model.pedido.item_pedido;


import com.pebatech.br.peba_delivery_api.domain.dto.pedido.item_pedido.ItemPedidoDto;
import com.pebatech.br.peba_delivery_api.domain.form.pedido.item_pedido.ItemPedidoForm;
import com.pebatech.br.peba_delivery_api.domain.model.ModeloGenerico;
import com.pebatech.br.peba_delivery_api.domain.model.pedido.Pedido;
import com.pebatech.br.peba_delivery_api.domain.model.produto.Produto;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter
@Table(name = "tb_item_pedido")
public class ItemPedido extends ModeloGenerico {

    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    private BigDecimal precoUnitario;

    @Column(nullable = false)
    private BigDecimal precoTotal;

    public ItemPedido toModel(ItemPedidoForm form){
        ItemPedido item = new ItemPedido();
        item.setQuantidade(form.getQuantidade());
        return item;
    }

    public static ItemPedidoDto toDto(ItemPedido itemPedido){
        ItemPedidoDto itemDto = new ItemPedidoDto();
        itemDto.setUuid(itemPedido.getUuid());
        itemDto.setUuidPedido(itemPedido.getPedido().getUuid());
        itemDto.setUuidProduto(itemPedido.getProduto().getUuid());
        itemDto.setNomeProduto(itemPedido.getProduto().getNome());
        itemDto.setQuantidade(itemPedido.getQuantidade());
        itemDto.setPrecoUnitario(itemPedido.getPrecoUnitario());
        itemDto.setPrecoTotal(itemPedido.getPrecoTotal());
        return itemDto;
    }
}

