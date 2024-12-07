package com.pebatech.br.peba_delivery_api.domain.dto.pedido;

import com.pebatech.br.peba_delivery_api.domain.dto.pedido.item_pedido.ItemPedidoDto;
import com.pebatech.br.peba_delivery_api.domain.enums.StatusPedidoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDto {

    private UUID uuid;
    private LocalDateTime dataPedido;
    private StatusPedidoEnum status;
    private UUID uuidCliente;
    private List<ItemPedidoDto> itens = new ArrayList<>();
    private BigDecimal valorTotal;
    private String observacao;
}
