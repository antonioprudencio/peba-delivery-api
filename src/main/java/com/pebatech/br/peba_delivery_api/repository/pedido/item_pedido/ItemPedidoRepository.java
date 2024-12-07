package com.pebatech.br.peba_delivery_api.repository.pedido.item_pedido;

import com.pebatech.br.peba_delivery_api.domain.model.pedido.item_pedido.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {
    Optional<ItemPedido> findByUuid(UUID uuid);

    boolean existsByUuid(UUID uuid);

    @Transactional
    @Modifying
    @Query("update ItemPedido i set i.ativo = ?1 where i.uuid = ?2")
    void updateAtivoByUuid(Boolean ativo, UUID uuid);
}
