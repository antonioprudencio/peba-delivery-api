package com.pebatech.br.peba_delivery_api.service.pedido.item_pedido.impl;


import com.pebatech.br.peba_delivery_api.domain.dto.pedido.item_pedido.ItemPedidoDto;
import com.pebatech.br.peba_delivery_api.domain.form.pedido.item_pedido.ItemPedidoForm;
import com.pebatech.br.peba_delivery_api.domain.model.pedido.Pedido;
import com.pebatech.br.peba_delivery_api.domain.model.pedido.item_pedido.ItemPedido;
import com.pebatech.br.peba_delivery_api.domain.model.produto.Produto;
import com.pebatech.br.peba_delivery_api.repository.pedido.PedidoRepository;
import com.pebatech.br.peba_delivery_api.repository.pedido.item_pedido.ItemPedidoRepository;
import com.pebatech.br.peba_delivery_api.repository.pessoa.PessoaRepository;
import com.pebatech.br.peba_delivery_api.repository.produto.ProdutoRepository;
import com.pebatech.br.peba_delivery_api.service.pedido.item_pedido.IItemPedidoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
public class ItemPedidoServiceImpl implements IItemPedidoService {

    @Autowired
    ItemPedidoRepository repository;

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    PessoaRepository pessoaRepository;

    @Autowired
    PedidoRepository pedidoRepository;


    @Override
    public ItemPedidoDto cadastrar(@NotNull ItemPedidoForm form, @NotNull UUID uuidPedido) {
        Produto produto = getProduto(form);

        ItemPedido itemPedidoToSalve = new ItemPedido();
        itemPedidoToSalve.setPedido(getPedido(uuidPedido));
        itemPedidoToSalve.setProduto(produto);
        itemPedidoToSalve.setQuantidade(form.getQuantidade());
        itemPedidoToSalve.setPrecoUnitario(produto.getPreco());
        itemPedidoToSalve.setPrecoTotal(itemPedidoToSalve.getPrecoUnitario().multiply(new BigDecimal(itemPedidoToSalve.getQuantidade())));
        ItemPedido itemPedido = repository.save(itemPedidoToSalve);
        return itemPedido.toDto(itemPedido);
    }

    private Pedido getPedido(UUID uuidPedido) {
        Optional<Pedido> pedidoOptional = pedidoRepository.findByUuid(uuidPedido);
        if (pedidoOptional.isEmpty()){
            throw new EntityNotFoundException("Pedido não encontrado para o ID informado!");
        }
        return pedidoOptional.get();
    }

    @Override
    public ItemPedidoDto atualizar(UUID idItemPedido, ItemPedidoForm form) {
        return null;
    }

    @Override
    public Page<ItemPedidoDto> listar(Pageable paginacao) {
        // TODO implementar filtros
        return null;
    }

    @Override
    public void excluir(UUID id) {
        repository.updateAtivoByUuid(Boolean.FALSE, id);
    }

    private Produto getProduto(ItemPedidoForm form){
        Optional<Produto> produtoOptional = produtoRepository.findByUuid(form.getUuidProduto());
        if (produtoOptional.isEmpty()) {
            throw new EntityNotFoundException("Produto não encontrado para o ID informado!");
        }
        return produtoOptional.get();
    }
}
