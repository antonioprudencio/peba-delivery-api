package com.pebatech.br.peba_delivery_api.service.pedido.item_pedido;

import com.pebatech.br.peba_delivery_api.domain.dto.pedido.item_pedido.ItemPedidoDto;
import com.pebatech.br.peba_delivery_api.domain.form.pedido.item_pedido.ItemPedidoForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IItemPedidoService {
    ItemPedidoDto cadastrar(ItemPedidoForm form, UUID uuidPedido);
    ItemPedidoDto atualizar(UUID idItemPedido, ItemPedidoForm form);
    Page<ItemPedidoDto> listar(Pageable paginacao);
    void excluir(UUID id);

}