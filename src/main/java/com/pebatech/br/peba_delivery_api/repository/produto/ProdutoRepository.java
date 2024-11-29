package com.pebatech.br.peba_delivery_api.repository.produto;

import com.pebatech.br.peba_delivery_api.domain.model.pessoa.Pessoa;
import com.pebatech.br.peba_delivery_api.domain.model.produto.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    Optional<Produto> findByUuid(UUID uuid);

    boolean existsByUuid(UUID uuid);

    @Transactional
    @Modifying
    @Query("update Produto p set p.ativo = ?1 where p.uuid = ?2")
    void updateAtivoByUuid(Boolean ativo, UUID uuid);
}
