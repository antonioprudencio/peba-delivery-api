package com.pebatech.br.peba_delivery_api.service.endereco;

import com.pebatech.br.peba_delivery_api.domain.dto.endereco.EnderecoDto;
import com.pebatech.br.peba_delivery_api.domain.form.endereco.EnderecoForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IEnderecoService {
    EnderecoDto cadastrar(EnderecoForm form);
    EnderecoDto atualizar(UUID idEndereco, EnderecoForm form);
    Page<EnderecoDto> listar(Pageable paginacao);
    void excluir(UUID id);

}
