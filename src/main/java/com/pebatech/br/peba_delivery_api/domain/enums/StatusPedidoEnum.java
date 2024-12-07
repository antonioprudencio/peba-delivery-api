package com.pebatech.br.peba_delivery_api.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusPedidoEnum {
    PENDENTE("Pendente"),
    APROVADO("Aprovado"),
    CANCELADO("Cancelado"),
    FINALIZADO("Finalizado");

    private final String descricao;
}
