package com.pebatech.br.peba_delivery_api.service.produto;

import com.pebatech.br.peba_delivery_api.domain.dto.produto.ProdutoDto;
import com.pebatech.br.peba_delivery_api.domain.form.produto.ProdutoForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IProdutoService {

    ProdutoDto cadastrar(ProdutoForm form);

    ProdutoDto atualizar(UUID idProduto, ProdutoForm form);

    Page<ProdutoDto> listar(Pageable paginacao);

    void excluir(UUID id);

}
