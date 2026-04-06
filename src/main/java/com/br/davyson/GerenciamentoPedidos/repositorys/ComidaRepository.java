package com.br.davyson.GerenciamentoPedidos.repositorys;

import com.br.davyson.GerenciamentoPedidos.entitys.Categoria;
import com.br.davyson.GerenciamentoPedidos.entitys.Comida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ComidaRepository extends JpaRepository<Comida, Long> {
    List<Comida> findByNomeContainingIgnoreCase(String nome);
    List<Comida> findByCategoriaNomeIgnoreCase(String nome);
    Optional<Comida> findByNomeIgnoreCase(String nome);
    boolean existsByNomeIgnoreCase(String name);
    boolean existsByCategoria(Categoria categoria);
    @Query("Select c FROM Comida c")
    List<Comida> showMenu();
}
