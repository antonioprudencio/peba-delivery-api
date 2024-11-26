package com.pebatech.br.peba_delivery_api.domain.form.pessoa;

import com.pebatech.br.peba_delivery_api.domain.enums.IdentidadeGeneroEnum;
import com.pebatech.br.peba_delivery_api.domain.form.contato.ContatoForm;
import com.pebatech.br.peba_delivery_api.domain.form.endereco.EnderecoForm;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class PessoaForm {

    private String nome;
    @NotEmpty(message = "CPF é obrigatório!")
    @NotBlank(message = "CPF é obrigatório!")
    private String cpf;
    private LocalDate dataNascimento;
    private IdentidadeGeneroEnum identidadeGenero;
    private List<EnderecoForm> enderecos;
    private List<ContatoForm> contatos;
}
