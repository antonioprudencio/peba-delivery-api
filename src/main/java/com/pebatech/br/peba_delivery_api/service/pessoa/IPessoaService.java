package com.pebatech.br.peba_delivery_api.service.pessoa;

import com.pebatech.br.peba_delivery_api.domain.dto.pessoa.PessoaDto;
import com.pebatech.br.peba_delivery_api.domain.form.pessoa.PessoaForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IPessoaService {

    PessoaDto cadastrar(PessoaForm form);
    PessoaDto atualizar(UUID idPessoa, PessoaForm form);
    Page<PessoaDto> listar(Pageable paginacao);
    void excluir(UUID id);

}
