package com.pebatech.br.peba_delivery_api.domain.form.produto;

import com.pebatech.br.peba_delivery_api.domain.enums.CategoriaProdutoEnum;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProdutoForm {

    @NotBlank(message = "O nome do produto é obrigatório!")
    @Size(max = 255, message = "O nome do produto não pode exceder 255 caracteres.")
    private String nome;

    @Size(max = 500, message = "A descrição do produto não pode exceder 500 caracteres.")
    private String descricao;

    @NotNull(message = "O preço do produto é obrigatório!")
    @DecimalMin(value = "0.0", inclusive = false, message = "O preço deve ser maior que zero.")
    private BigDecimal preco;

    @NotNull(message = "A categoria do produto é obrigatória!")
    private CategoriaProdutoEnum categoria;

    @NotNull(message = "A disponibilidade do produto deve ser informada!")
    private Boolean ativo;
}

