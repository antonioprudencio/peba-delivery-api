package com.pebatech.br.peba_delivery_api.domain.model.pessoa;

import com.pebatech.br.peba_delivery_api.domain.enums.IdentidadeGeneroEnum;
import com.pebatech.br.peba_delivery_api.domain.model.ModeloGenerico;
import com.pebatech.br.peba_delivery_api.domain.model.contato.Contato;
import com.pebatech.br.peba_delivery_api.domain.model.endereco.Endereco;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "tb_pessoa")
public class Pessoa extends ModeloGenerico {

    @Column(name = "nome")
    private String nome;

    @Column(unique = true, name = "cpf", length = 14)
    private String cpf;

    @Column(name = "data_nascimento", length = 50)
    private LocalDate dataNascimento;

    @Column(name = "identidade_genero", length = 50)
    @Enumerated(EnumType.STRING)
    private IdentidadeGeneroEnum identidadeGenero;


    @OneToMany(mappedBy = "pessoa")
    private List<Endereco> enderecos = new ArrayList<>();

    @OneToMany(mappedBy = "pessoa")
    private List<Contato> contatos = new ArrayList<>();

}

