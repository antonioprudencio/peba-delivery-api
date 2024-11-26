package com.pebatech.br.peba_delivery_api.repository.endereco;

import com.pebatech.br.peba_delivery_api.domain.model.endereco.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    Optional<Endereco> findByUuid(UUID idEndereco);

    @Transactional
    @Modifying
    @Query("update Endereco e set e.ativo = ?1 where e.uuid = ?2")
    void updateAtivoByUuid(Boolean ativo, UUID uuid);
}
