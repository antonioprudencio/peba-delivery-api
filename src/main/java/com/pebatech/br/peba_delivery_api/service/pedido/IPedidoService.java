package com.pebatech.br.peba_delivery_api.service.pedido;

import com.pebatech.br.peba_delivery_api.domain.dto.pedido.PedidoDto;
import com.pebatech.br.peba_delivery_api.domain.form.pedido.PedidoForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IPedidoService {
    PedidoDto cadastrar(PedidoForm form);
    PedidoDto atualizar(UUID idPedido, PedidoForm form);
    Page<PedidoDto> listar(Pageable paginacao);
    void excluir(UUID id);

}