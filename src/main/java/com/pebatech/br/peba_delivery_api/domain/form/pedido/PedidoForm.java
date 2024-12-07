package com.pebatech.br.peba_delivery_api.domain.form.pedido;

import com.pebatech.br.peba_delivery_api.domain.enums.StatusPedidoEnum;
import com.pebatech.br.peba_delivery_api.domain.form.pedido.item_pedido.ItemPedidoForm;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class PedidoForm {

    @NotNull(message = "O cliente é obrigatório!")
    private UUID uuidCliente; // Referência ao cliente que fez o pedido.

    @NotNull(message = "O status do pedido é obrigatório!")
    private StatusPedidoEnum status; // Ex.: "PENDENTE", "APROVADO".

    private List<ItemPedidoForm> itens; // Lista de itens no pedido.

    private String observacao; // Observações adicionais.
}
