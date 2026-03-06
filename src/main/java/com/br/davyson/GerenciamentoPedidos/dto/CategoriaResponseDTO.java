package com.br.davyson.GerenciamentoPedidos.dto;

import com.br.davyson.GerenciamentoPedidos.entitys.Categoria;

public record CategoriaResponseDTO (Long id, String nome){

    public CategoriaResponseDTO(Categoria categoria){
       this(categoria.getId(), categoria.getNome());
    }

}
