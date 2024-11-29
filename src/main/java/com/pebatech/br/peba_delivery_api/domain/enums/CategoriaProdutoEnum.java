package com.pebatech.br.peba_delivery_api.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CategoriaProdutoEnum {
    ENTRADA("Entrada"),
    PRATO_PRINCIPAL("Prato Principal"),
    SOBREMESA("Sobremesa"),
    BEBIDA("Bebida"),
    ACOMPANHAMENTO("Acompanhamento");

    private final String descricao;
}
