package com.pebatech.br.peba_delivery_api.domain.mapper.endereco;

import com.pebatech.br.peba_delivery_api.domain.dto.contato.ContatoDto;
import com.pebatech.br.peba_delivery_api.domain.dto.endereco.EnderecoDto;
import com.pebatech.br.peba_delivery_api.domain.form.contato.ContatoForm;
import com.pebatech.br.peba_delivery_api.domain.form.endereco.EnderecoForm;
import com.pebatech.br.peba_delivery_api.domain.mapper.EntityMapper;
import com.pebatech.br.peba_delivery_api.domain.model.contato.Contato;
import com.pebatech.br.peba_delivery_api.domain.model.endereco.Endereco;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EnderecoMapper extends EntityMapper<EnderecoDto, Endereco, EnderecoForm> {
    Endereco map(EnderecoDto value);

    void updateModel(EnderecoForm form, @MappingTarget Endereco endereco);
}
