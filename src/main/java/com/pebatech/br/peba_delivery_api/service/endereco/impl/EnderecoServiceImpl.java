package com.pebatech.br.peba_delivery_api.service.endereco.impl;

import com.pebatech.br.peba_delivery_api.domain.dto.endereco.EnderecoDto;
import com.pebatech.br.peba_delivery_api.domain.form.endereco.EnderecoForm;
import com.pebatech.br.peba_delivery_api.domain.mapper.endereco.EnderecoMapper;
import com.pebatech.br.peba_delivery_api.domain.model.endereco.Endereco;
import com.pebatech.br.peba_delivery_api.repository.endereco.EnderecoRepository;
import com.pebatech.br.peba_delivery_api.repository.pessoa.PessoaRepository;
import com.pebatech.br.peba_delivery_api.service.endereco.IEnderecoService;
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
public class EnderecoServiceImpl implements IEnderecoService {
    
    @Autowired
    EnderecoRepository repository;
    @Autowired
    PessoaRepository pessoaRepository;
    @Autowired
    private EnderecoMapper mapper;


    @Override
    public EnderecoDto cadastrar(EnderecoForm form) {
        Endereco EnderecoSalvar = mapper.toModel(form);
        // TODO adicionar validação do valor de Endereco de acordo com o tipo
        if (Objects.nonNull(form.getUuidPessoa()) && !pessoaRepository.existsByUuid(form.getUuidPessoa())){
            throw new EntityNotFoundException("Nenhuma pessoa localizada para o id informado!");
        }
        Endereco EnderecoSalvo = repository.save(EnderecoSalvar);
        return mapper.toDto(EnderecoSalvo);
    }

    @Override
    public EnderecoDto atualizar(UUID idEndereco, EnderecoForm form) {
        Optional<Endereco> EnderecoOptional = repository.findByUuid(idEndereco);
        // TODO adicionar validação do valor de Endereco de acordo com o tipo
        if (EnderecoOptional.isEmpty()){
            throw new EntityNotFoundException("Nenhuma Endereco localizada para o id informado!");
        }
        Endereco Endereco = EnderecoOptional.get();
        mapper.updateModel(form,Endereco);
        return mapper.toDto(repository.save(Endereco));
    }

    @Override
    public Page<EnderecoDto> listar(Pageable paginacao) {
        // TODO implementar filtros
        return repository.findAll(paginacao).map(mapper::toDto);
    }

    @Override
    public void excluir(UUID id) {
        repository.updateAtivoByUuid(Boolean.FALSE,id);
    }
}
