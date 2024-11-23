package com.pebatech.br.peba_delivery_api.domain.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class ModeloGenerico implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Version
    Long versao;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, updatable = false, nullable = false)
    private UUID uuid = UUID.randomUUID();

    @Column(columnDefinition = "boolean default true")
    private Boolean ativo = Boolean.TRUE;

    @CreationTimestamp
    private LocalDateTime dataCriacao;

    @UpdateTimestamp
    private LocalDateTime dataAtualizacao;
}
