package com.pebatech.br.peba_delivery_api.repository.pedido;

import com.pebatech.br.peba_delivery_api.domain.model.pedido.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    Optional<Pedido> findByUuid(UUID uuid);

    boolean existsByUuid(UUID uuid);

    @Transactional
    @Modifying
    @Query("update Pedido p set p.ativo = ?1 where p.uuid = ?2")
    void updateAtivoByUuid(Boolean ativo, UUID uuid);

    @Transactional
    @Modifying
    @Query("update Pedido p set p.valorTotal = ?1 where p.uuid = ?2")
    void updateValorTotalByUuid(BigDecimal valorTotal, UUID uuid);
}
