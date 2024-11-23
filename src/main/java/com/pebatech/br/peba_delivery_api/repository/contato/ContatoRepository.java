package com.pebatech.br.peba_delivery_api.repository.contato;

import com.pebatech.br.peba_delivery_api.domain.model.contato.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

public interface ContatoRepository  extends JpaRepository<Contato, Long> {
    Optional<Contato> findByUuid(UUID idContato);

    @Transactional
    @Modifying
    @Query("update Contato c set c.ativo = ?1 where c.uuid = ?2")
    void updateAtivoByUuid(Boolean ativo, UUID uuid);
}
