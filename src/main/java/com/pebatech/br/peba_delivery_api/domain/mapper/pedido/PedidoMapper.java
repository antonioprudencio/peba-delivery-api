package com.pebatech.br.peba_delivery_api.domain.mapper.pedido;


import com.pebatech.br.peba_delivery_api.domain.dto.pedido.PedidoDto;
import com.pebatech.br.peba_delivery_api.domain.form.pedido.PedidoForm;
import com.pebatech.br.peba_delivery_api.domain.form.pedido.item_pedido.ItemPedidoForm;
import com.pebatech.br.peba_delivery_api.domain.mapper.EntityMapper;
import com.pebatech.br.peba_delivery_api.domain.model.pedido.Pedido;
import com.pebatech.br.peba_delivery_api.domain.model.pedido.item_pedido.ItemPedido;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PedidoMapper extends EntityMapper<PedidoDto, Pedido, PedidoForm> {
    Pedido map(PedidoDto value);
    ItemPedidoForm map(ItemPedido value);
    void updateModel(PedidoForm form, @MappingTarget Pedido pedido);
}