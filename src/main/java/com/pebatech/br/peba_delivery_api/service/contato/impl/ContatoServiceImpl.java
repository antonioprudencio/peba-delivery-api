package com.pebatech.br.peba_delivery_api.service.contato.impl;

import com.pebatech.br.peba_delivery_api.domain.dto.contato.ContatoDto;
import com.pebatech.br.peba_delivery_api.domain.form.contato.ContatoForm;
import com.pebatech.br.peba_delivery_api.domain.mapper.contato.ContatoMapper;
import com.pebatech.br.peba_delivery_api.domain.model.contato.Contato;
import com.pebatech.br.peba_delivery_api.domain.model.pessoa.Pessoa;
import com.pebatech.br.peba_delivery_api.repository.contato.ContatoRepository;
import com.pebatech.br.peba_delivery_api.repository.pessoa.PessoaRepository;
import com.pebatech.br.peba_delivery_api.service.contato.IContatoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

// TODO isolar validações em uma classe
@Service
public class ContatoServiceImpl implements IContatoService {
    
    @Autowired
    ContatoRepository repository;
    @Autowired
    PessoaRepository pessoaRepository;
    @Autowired
    private ContatoMapper mapper;

    @Override
    public ContatoDto cadastrar(ContatoForm form) {
        Contato contatoSalvar = mapper.toModel(form);
        // TODO adicionar validação do valor de contato de acordo com o tipo
        if (Objects.nonNull(form.getUuidPessoa())){
            Optional<Pessoa> pessoa = pessoaRepository.findByUuid(form.getUuidPessoa());
            if (pessoa.isEmpty()){
                throw new EntityNotFoundException("Nenhuma pessoa localizada para o id informado!");
            }else {
                contatoSalvar.setPessoa(pessoa.get());
            }
        }
        Contato ContatoSalvo = repository.save(contatoSalvar);
        return mapper.toDto(ContatoSalvo);
    }

    @Override
    public ContatoDto atualizar(UUID idContato, ContatoForm form) {
        Optional<Contato> ContatoOptional = repository.findByUuid(idContato);
        // TODO adicionar validação do valor de contato de acordo com o tipo
        if (ContatoOptional.isEmpty()){
            throw new EntityNotFoundException("Nenhuma Contato localizada para o id informado!");
        }
        Contato contato = ContatoOptional.get();
        if (!contato.getTipo().equals(form.getTipo())){
            throw new EntityNotFoundException("Não é possível atualizar um contato com o tipo de origem diferente do atual!");
        }
        mapper.updateModel(form,contato);
        return mapper.toDto(repository.save(contato));
    }

    @Override
    public Page<ContatoDto> listar(Pageable paginacao) {
        // TODO implementar filtros
        return repository.findAll(paginacao).map(mapper::toDto);
    }

    @Override
    public void excluir(UUID id) {
        repository.updateAtivoByUuid(Boolean.FALSE,id);
    }
}
