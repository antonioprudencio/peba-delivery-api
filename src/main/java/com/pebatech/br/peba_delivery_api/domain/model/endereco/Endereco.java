package com.pebatech.br.peba_delivery_api.domain.model.endereco;

import com.pebatech.br.peba_delivery_api.domain.model.ModeloGenerico;
import com.pebatech.br.peba_delivery_api.domain.model.pessoa.Pessoa;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_endereco")
public class Endereco extends ModeloGenerico {

    private String logradouro;

    @Column(length = 10)
    private String numero;

    private String bairro;

    private String cidade;

    @Column(length = 10)
    private String cep;

    private String complemento;

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;
}
