package com.pebatech.br.peba_delivery_api.domain.form.contato;


import com.pebatech.br.peba_delivery_api.domain.enums.TipoContato;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class ContatoForm {

    @Enumerated(EnumType.STRING)
    private TipoContato tipo;
    private String valor;
    private UUID uuidPessoa;
}
