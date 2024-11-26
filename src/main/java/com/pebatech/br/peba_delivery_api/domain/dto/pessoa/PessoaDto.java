package com.pebatech.br.peba_delivery_api.domain.dto.pessoa;

import com.pebatech.br.peba_delivery_api.domain.dto.contato.ContatoDto;
import com.pebatech.br.peba_delivery_api.domain.dto.endereco.EnderecoDto;
import com.pebatech.br.peba_delivery_api.domain.enums.IdentidadeGeneroEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaDto {

    private UUID uuid;
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private IdentidadeGeneroEnum identidadeGenero;
    private List<EnderecoDto> enderecos;
    private List<ContatoDto> contatos;

}
