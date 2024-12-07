package com.pebatech.br.peba_delivery_api.service.pedido.impl;


import com.pebatech.br.peba_delivery_api.domain.dto.pedido.PedidoDto;
import com.pebatech.br.peba_delivery_api.domain.dto.pedido.item_pedido.ItemPedidoDto;
import com.pebatech.br.peba_delivery_api.domain.form.pedido.PedidoForm;
import com.pebatech.br.peba_delivery_api.domain.form.pedido.item_pedido.ItemPedidoForm;
import com.pebatech.br.peba_delivery_api.domain.mapper.pedido.PedidoMapper;
import com.pebatech.br.peba_delivery_api.domain.mapper.pedido.item_pedido.ItemPedidoMapper;
import com.pebatech.br.peba_delivery_api.domain.model.pedido.Pedido;
import com.pebatech.br.peba_delivery_api.domain.model.pessoa.Pessoa;
import com.pebatech.br.peba_delivery_api.domain.model.produto.Produto;
import com.pebatech.br.peba_delivery_api.repository.pedido.PedidoRepository;
import com.pebatech.br.peba_delivery_api.repository.pessoa.PessoaRepository;
import com.pebatech.br.peba_delivery_api.repository.produto.ProdutoRepository;
import com.pebatech.br.peba_delivery_api.service.pedido.IPedidoService;
import com.pebatech.br.peba_delivery_api.service.pedido.item_pedido.impl.ItemPedidoServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class PedidoServiceImpl implements IPedidoService {

    @Autowired
    PedidoRepository repository;

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    PessoaRepository pessoaRepository;

    @Autowired
    private PedidoMapper mapper;

    @Autowired
    private ItemPedidoServiceImpl itemPedidoService;

    @Autowired
    private ItemPedidoMapper itemPedidoMapper;

    @Transactional
    @Override
    public PedidoDto cadastrar(PedidoForm form) {
        List<ItemPedidoForm> itemPedidoFormList = getItemPedidoForms(form);
        form.setItens(new ArrayList<>());

        Pedido pedidoParaSalvar = mapper.toModel(form);
        setCliente(form.getUuidCliente(), pedidoParaSalvar);
        repository.save(pedidoParaSalvar);
        PedidoDto pedidoDto = pedidoParaSalvar.toDto();

        if (Objects.nonNull(itemPedidoFormList) && !itemPedidoFormList.isEmpty()) {
            itemPedidoFormList.forEach(itemForm -> {
                Optional<Produto> produtoOptional = produtoRepository.findByUuid(itemForm.getUuidProduto());
                if (produtoOptional.isEmpty()) {
                    throw new EntityNotFoundException("Produto não encontrado para o ID informado!");
                }
                Produto produto = produtoOptional.get();
                itemForm.setUuidProduto(produto.getUuid());
                ItemPedidoDto item = itemPedidoService.cadastrar(itemForm, pedidoParaSalvar.getUuid());
                pedidoDto.getItens().add(item);
            });
        }else {
            throw new EntityNotFoundException("O pedido deve conter pelo menos um item!");
        }

        // TODO mover cálculo para o getValorTotal
        BigDecimal total = pedidoDto.getItens().stream()
                .map(item -> item.getPrecoUnitario()
                        .multiply(BigDecimal.valueOf(item.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        pedidoDto.setValorTotal(total);
        pedidoParaSalvar.setValorTotal(total);
        repository.save(pedidoParaSalvar);

        return pedidoDto;
    }

    @Override
    public PedidoDto atualizar(UUID idPedido, PedidoForm form) {
        Optional<Pedido> pedidoOptional = repository.findByUuid(idPedido);
        if (pedidoOptional.isEmpty()){
            throw new EntityNotFoundException("Nenhum pedido localizado com o id informado!");
        }
        if (Objects.nonNull(form.getObservacao()) && !Objects.equals(form.getObservacao(),pedidoOptional.get().getObservacao())){
            pedidoOptional.get().setObservacao(form.getObservacao());
        }

        return null;
    }

    private List<ItemPedidoForm> getItemPedidoForms(PedidoForm form) {
        return form.getItens();
    }

    private void setCliente(UUID uuidCliente, Pedido pedido) {
        Optional<Pessoa> clienteOptional = pessoaRepository.findByUuid(uuidCliente);
        if (clienteOptional.isEmpty()){
            throw new EntityNotFoundException("Nenhum cliente localizado com o id informado!");
        }
        pedido.setCliente(clienteOptional.get());
    }

    @Override
    public Page<PedidoDto> listar(Pageable paginacao) {
        // TODO implementar filtros
        return repository.findAll(paginacao)
                .map(Pedido::toDto);
    }

    @Override
    public void excluir(UUID id) {
        repository.updateAtivoByUuid(Boolean.FALSE, id);
    }
}
