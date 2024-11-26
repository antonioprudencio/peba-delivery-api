package com.pebatech.br.peba_delivery_api.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum IdentidadeGeneroEnum {
    CISGENERO("Cisgênero"),
    TRANSGENERO("Transgênero"),
    NAO_BINARIO("Não binário");

    private final String descricao;
}
