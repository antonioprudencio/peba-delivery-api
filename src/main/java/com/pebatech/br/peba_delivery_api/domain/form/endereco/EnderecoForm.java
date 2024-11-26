package com.pebatech.br.peba_delivery_api.domain.form.endereco;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EnderecoForm {

    private UUID uuidPessoa;
    private String logradouro;
    private String numero;
    private String bairro;
    private String cidade;
    private String cep;
    private String complemento;
}
