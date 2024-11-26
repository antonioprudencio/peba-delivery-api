package com.pebatech.br.peba_delivery_api.service.pessoa.impl;

import com.pebatech.br.peba_delivery_api.domain.dto.contato.ContatoDto;
import com.pebatech.br.peba_delivery_api.domain.dto.pessoa.PessoaDto;
import com.pebatech.br.peba_delivery_api.domain.form.pessoa.PessoaForm;
import com.pebatech.br.peba_delivery_api.domain.mapper.pessoa.PessoaMapper;
import com.pebatech.br.peba_delivery_api.domain.model.contato.Contato;
import com.pebatech.br.peba_delivery_api.domain.model.pessoa.Pessoa;
import com.pebatech.br.peba_delivery_api.repository.pessoa.PessoaRepository;
import com.pebatech.br.peba_delivery_api.service.contato.IContatoService;
import com.pebatech.br.peba_delivery_api.service.endereco.IEnderecoService;
import com.pebatech.br.peba_delivery_api.service.pessoa.IPessoaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
// TODO isolar validações em uma classe
@Service
public class PessoaServiceImpl implements IPessoaService {

    @Autowired
    PessoaRepository repository;
    @Autowired
    private PessoaMapper mapper;
    @Autowired
    private IContatoService contatoService;
    @Autowired
    private IEnderecoService enderecoService;


    @Override
    public PessoaDto cadastrar(PessoaForm form) {
        Pessoa pessoaSalva = repository.save(mapper.toModel(form));
//        PessoaDto pessoaDto = mapper.toDto(pessoaSalva);
        if (Objects.nonNull(form.getContatos()) && !form.getContatos().isEmpty()){
            form.getContatos().forEach(c -> {
                c.setUuidPessoa(pessoaSalva.getUuid());
                contatoService.cadastrar(c);
            });
        }
        if (Objects.nonNull(form.getEnderecos()) && !form.getEnderecos().isEmpty()){
            form.getEnderecos().forEach(e -> {
                e.setUuidPessoa(pessoaSalva.getUuid());
                enderecoService.cadastrar(e);
            });
        }

        return mapper.toDto(pessoaSalva);
    }

    @Override
    public PessoaDto atualizar(UUID idPessoa, PessoaForm form) {
        Optional<Pessoa> pessoaOptional = repository.findByUuid(idPessoa);
        if (pessoaOptional.isEmpty()){
            throw new EntityNotFoundException("Nenhuma pessoa localizada para o id informado!");
        }
        Pessoa pessoa = pessoaOptional.get();
        mapper.updateModel(form,pessoa);
        return mapper.toDto(repository.save(pessoa));
    }

    @Override
    public Page<PessoaDto> listar(Pageable paginacao) {
        // TODO implementar filtros
        return repository.findAll(paginacao).map(mapper::toDto);
    }

    @Override
    public void excluir(UUID id) {
        repository.updateAtivoByUuid(Boolean.FALSE,id);
    }
}
