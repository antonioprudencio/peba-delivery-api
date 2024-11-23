package com.pebatech.br.peba_delivery_api.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoContato {
    TELEFONE("Telefone"),
    EMAIL("E-mail");

    private final String descricao;
}