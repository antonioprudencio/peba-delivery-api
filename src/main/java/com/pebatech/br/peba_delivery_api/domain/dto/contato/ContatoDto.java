package com.pebatech.br.peba_delivery_api.domain.dto.contato;

import com.pebatech.br.peba_delivery_api.domain.enums.TipoContato;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContatoDto {

    @Enumerated(EnumType.STRING)
    private TipoContato tipo;
    private String valor;
    private UUID uuid;
    private UUID uuidPessoa;
    private Boolean ativo;
}
