package com.br.davyson.GerenciamentoPedidos.dto;

import java.math.BigDecimal;

public record ComidaRequestDTO(String nome, String descricao, BigDecimal preco) {
}
