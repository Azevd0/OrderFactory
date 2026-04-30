package com.br.davyson.GerenciamentoPedidos.repositorys;

import com.br.davyson.GerenciamentoPedidos.entitys.Pedido;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @EntityGraph(attributePaths = {"comidas", "atendente"})
    Optional<Pedido> findByMesa(Integer mesa);
    boolean existsByMesa(Integer mesa);

}
