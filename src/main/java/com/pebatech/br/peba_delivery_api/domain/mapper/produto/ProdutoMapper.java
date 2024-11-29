package com.pebatech.br.peba_delivery_api.domain.mapper.produto;

import com.pebatech.br.peba_delivery_api.domain.dto.pessoa.PessoaDto;
import com.pebatech.br.peba_delivery_api.domain.dto.produto.ProdutoDto;
import com.pebatech.br.peba_delivery_api.domain.form.pessoa.PessoaForm;
import com.pebatech.br.peba_delivery_api.domain.form.produto.ProdutoForm;
import com.pebatech.br.peba_delivery_api.domain.mapper.EntityMapper;
import com.pebatech.br.peba_delivery_api.domain.model.pessoa.Pessoa;
import com.pebatech.br.peba_delivery_api.domain.model.produto.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProdutoMapper extends EntityMapper<ProdutoDto, Produto, ProdutoForm> {
    Produto map(ProdutoDto value);
    void updateModel(ProdutoForm form, @MappingTarget Produto produto);

}
