package com.pebatech.br.peba_delivery_api.domain.dto.pedido.item_pedido;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedidoDto {

    private UUID uuid;
    private UUID uuidPedido;
    private UUID uuidProduto;
    private String nomeProduto;
    private Integer quantidade;
    private BigDecimal precoUnitario;
    private BigDecimal precoTotal;
}

