package com.pebatech.br.peba_delivery_api.repository.pessoa;

import com.pebatech.br.peba_delivery_api.domain.model.pessoa.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    Optional<Pessoa> findByUuid(UUID uuid);

    boolean existsByUuid(UUID uuid);

    @Transactional
    @Modifying
    @Query("update Pessoa p set p.ativo = ?1 where p.uuid = ?2")
    void updateAtivoByUuid(Boolean ativo, UUID uuid);
}
