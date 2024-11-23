package com.pebatech.br.peba_delivery_api.service.contato;

import com.pebatech.br.peba_delivery_api.domain.dto.contato.ContatoDto;
import com.pebatech.br.peba_delivery_api.domain.form.contato.ContatoForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IContatoService {
    ContatoDto cadastrar(ContatoForm form);
    ContatoDto atualizar(UUID idContato, ContatoForm form);
    Page<ContatoDto> listar(Pageable paginacao);
    void excluir(UUID id);

}
