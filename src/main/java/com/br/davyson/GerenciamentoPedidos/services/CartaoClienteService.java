package com.br.davyson.GerenciamentoPedidos.services;

import com.br.davyson.GerenciamentoPedidos.dto.response.CartaoClienteResponseDTO;
import com.br.davyson.GerenciamentoPedidos.entitys.CartaoCliente;
import com.br.davyson.GerenciamentoPedidos.enums.BandeiraCartao;
import com.br.davyson.GerenciamentoPedidos.enums.ModalidadaCartao;
import com.br.davyson.GerenciamentoPedidos.exceptions.ObjectNotFoundException;
import com.br.davyson.GerenciamentoPedidos.repositorys.CartaoClienteRepository;
import com.br.davyson.GerenciamentoPedidos.wrapper.ListWrapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartaoClienteService {
    private final CartaoClienteRepository repository;

    public CartaoClienteService(CartaoClienteRepository repository) {
        this.repository = repository;
    }

    @CacheEvict(value = "clientes", allEntries = true)
    public CartaoClienteResponseDTO registrarCartao(String senha, BigDecimal saldo, BandeiraCartao bandeira, ModalidadaCartao modalidade){
         CartaoCliente cartaoRegistrado = new CartaoCliente();
         cartaoRegistrado.setSaldo(saldo);
         cartaoRegistrado.setSenha(senha);
         cartaoRegistrado.setBandeiraCartao(bandeira);
         cartaoRegistrado.setFormaPagamento(modalidade);
         repository.save(cartaoRegistrado);
         return new CartaoClienteResponseDTO(cartaoRegistrado);
    }

    @CachePut(value = "clientes", key = "'all_cards'")
    public ListWrapper<CartaoClienteResponseDTO> listarCartoes() {
        List<CartaoClienteResponseDTO> cartoes = repository.findAll()
                .stream().map(CartaoClienteResponseDTO::new).collect(Collectors.toList());
        return new ListWrapper<>(cartoes);
    }
    @CachePut(value = "clientes", key = "'card_' + #id")
    public CartaoClienteResponseDTO buscarPorId(Long id) {
        CartaoCliente cartao = repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Cartão não encontrado com o ID: " + id));
        return new CartaoClienteResponseDTO(cartao);
    }

    @CacheEvict(value = "clientes", allEntries = true)
    public void deletarCartao(Long id){
        CartaoCliente cartaoRemovido = repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Cartão não encontrado para remoção"));
        repository.delete(cartaoRemovido);
    }
}
