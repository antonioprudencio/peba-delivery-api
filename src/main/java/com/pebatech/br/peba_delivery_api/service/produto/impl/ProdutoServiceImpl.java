package com.pebatech.br.peba_delivery_api.service.produto.impl;

import com.pebatech.br.peba_delivery_api.domain.dto.produto.ProdutoDto;
import com.pebatech.br.peba_delivery_api.domain.form.produto.ProdutoForm;
import com.pebatech.br.peba_delivery_api.domain.mapper.produto.ProdutoMapper;
import com.pebatech.br.peba_delivery_api.domain.model.produto.Produto;
import com.pebatech.br.peba_delivery_api.repository.produto.ProdutoRepository;
import com.pebatech.br.peba_delivery_api.service.produto.IProdutoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProdutoServiceImpl implements IProdutoService {

    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private ProdutoMapper mapper;

    @Override
    public ProdutoDto cadastrar(ProdutoForm form) {
        Produto produtoSalvo = repository.save(mapper.toModel(form));
        return mapper.toDto(produtoSalvo);
    }

    @Override
    public ProdutoDto atualizar(UUID idProduto, ProdutoForm form) {
        Optional<Produto> produtoOptional = repository.findByUuid(idProduto);
        if (produtoOptional.isEmpty()) {
            throw new EntityNotFoundException("Nenhum produto localizado para o ID informado!");
        }
        Produto produto = produtoOptional.get();
        mapper.updateModel(form, produto);
        return mapper.toDto(repository.save(produto));
    }

    @Override
    public Page<ProdutoDto> listar(Pageable paginacao) {
        return repository.findAll(paginacao).map(mapper::toDto);
    }

    @Override
    public void excluir(UUID idProduto) {
        repository.updateAtivoByUuid(Boolean.FALSE, idProduto);
    }
}

