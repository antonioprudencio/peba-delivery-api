package com.pebatech.br.peba_delivery_api.domain.form.pedido.item_pedido;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Setter
@Getter
public class ItemPedidoForm {

    private UUID uuidProduto;

    @Min(value = 1, message = "A quantidade mínima é 1!")
    private Integer quantidade;

}
