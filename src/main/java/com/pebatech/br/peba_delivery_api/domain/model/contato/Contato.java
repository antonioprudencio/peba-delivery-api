package com.pebatech.br.peba_delivery_api.domain.model.contato;

import com.pebatech.br.peba_delivery_api.domain.enums.TipoContato;
import com.pebatech.br.peba_delivery_api.domain.model.ModeloGenerico;
import com.pebatech.br.peba_delivery_api.domain.model.pessoa.Pessoa;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "tb_contato")
public class Contato extends ModeloGenerico {

    @Enumerated(EnumType.STRING)
    private TipoContato tipo;

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    private String valor;
}
