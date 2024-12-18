package com.pebatech.br.peba_delivery_api.domain.mapper.contato;

import com.pebatech.br.peba_delivery_api.domain.dto.contato.ContatoDto;
import com.pebatech.br.peba_delivery_api.domain.form.contato.ContatoForm;
import com.pebatech.br.peba_delivery_api.domain.mapper.EntityMapper;
import com.pebatech.br.peba_delivery_api.domain.model.contato.Contato;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ContatoMapper extends EntityMapper<ContatoDto, Contato, ContatoForm> {

    Contato map(ContatoDto value);

    @Mappings({
            @Mapping(target = "uuidPessoa", source = "pessoa.uuid")
    })
    ContatoDto toDto(Contato value);

    void updateModel(ContatoForm form, @MappingTarget Contato contato);
}
