package com.pebatech.br.peba_delivery_api.domain.mapper.pessoa;

import com.pebatech.br.peba_delivery_api.domain.dto.pessoa.PessoaDto;
import com.pebatech.br.peba_delivery_api.domain.form.pessoa.PessoaForm;
import com.pebatech.br.peba_delivery_api.domain.mapper.EntityMapper;
import com.pebatech.br.peba_delivery_api.domain.model.pessoa.Pessoa;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PessoaMapper extends EntityMapper<PessoaDto, Pessoa, PessoaForm> {
    Pessoa map(PessoaDto value);
    void updateModel(PessoaForm form, @MappingTarget Pessoa contato);

}
