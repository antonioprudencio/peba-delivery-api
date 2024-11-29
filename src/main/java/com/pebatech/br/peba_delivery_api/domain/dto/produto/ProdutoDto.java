package com.pebatech.br.peba_delivery_api.domain.dto.produto;

import com.pebatech.br.peba_delivery_api.domain.enums.CategoriaProdutoEnum;
import lombok.*;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProdutoDto {

    private UUID uuid;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private CategoriaProdutoEnum categoria;
    private Boolean ativo;
}

