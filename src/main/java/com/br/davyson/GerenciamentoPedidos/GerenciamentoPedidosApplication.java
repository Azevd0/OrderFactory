package com.br.davyson.GerenciamentoPedidos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class GerenciamentoPedidosApplication {

	public static void main(String[] args) {
		SpringApplication.run(GerenciamentoPedidosApplication.class, args);
	}

}
