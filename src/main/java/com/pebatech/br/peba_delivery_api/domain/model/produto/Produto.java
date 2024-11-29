package com.pebatech.br.peba_delivery_api.domain.model.produto;

import com.pebatech.br.peba_delivery_api.domain.enums.CategoriaProdutoEnum;
import com.pebatech.br.peba_delivery_api.domain.model.ModeloGenerico;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "tb_produto")
public class Produto extends ModeloGenerico {

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(length = 500)
    private String descricao;

    @Column(nullable = false)
    private BigDecimal preco;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoriaProdutoEnum categoria;

    @Column(nullable = false)
    private Boolean disponivel = Boolean.TRUE;

}
