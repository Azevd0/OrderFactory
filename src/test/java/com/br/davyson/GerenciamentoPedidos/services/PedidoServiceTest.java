package com.br.davyson.GerenciamentoPedidos.services;

import com.br.davyson.GerenciamentoPedidos.config.JWTUserData;
import com.br.davyson.GerenciamentoPedidos.dto.request.PedidoRequestDTO;
import com.br.davyson.GerenciamentoPedidos.dto.response.PedidoResponseDTO;
import com.br.davyson.GerenciamentoPedidos.entitys.Atendente;
import com.br.davyson.GerenciamentoPedidos.entitys.Comanda;
import com.br.davyson.GerenciamentoPedidos.entitys.Comida;
import com.br.davyson.GerenciamentoPedidos.entitys.Pedido;
import com.br.davyson.GerenciamentoPedidos.repositorys.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
class PedidoServiceTest {

    private PedidoRepository pedidoRepository;
    private ComidaRepository comidaRepository;
    private ReciboRepository reciboRepository;
    private ComidaService comidaService;
    private ComandaRepository comandaRepository;
    private CartaoClienteRepository cartaoClienteRepository;
    private AtendenteService atendenteService;

    private PedidoService pedidoService;

    @BeforeEach
    void setUp() {
        pedidoRepository = Mockito.mock(PedidoRepository.class);
        comidaRepository = Mockito.mock(ComidaRepository.class);
        reciboRepository = Mockito.mock(ReciboRepository.class);
        comidaService = Mockito.mock(ComidaService.class);
        comandaRepository = Mockito.mock(ComandaRepository.class);
        cartaoClienteRepository = Mockito.mock(CartaoClienteRepository.class);
        atendenteService = Mockito.mock(AtendenteService.class);

        pedidoService = new PedidoService(
                pedidoRepository, comidaRepository, reciboRepository,
                comidaService, comandaRepository, cartaoClienteRepository, atendenteService
        );

        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);

        JWTUserData jwtUserData = new JWTUserData(1L, "admin@restaurante.com", "ADMIN");
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    @DisplayName("Lançar pedido em mesa livre")
    void deveCriarPedidoEComandaQuandoMesaLivreEComidasValidas() {
        JWTUserData jwtUserData = new JWTUserData(1L, "admin@rgmail.com", "ADMIN");

        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);

        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        Mockito.when(authentication.getPrincipal()).thenReturn(jwtUserData);
        SecurityContextHolder.setContext(securityContext);
        String emailLogado = jwtUserData.email();

        PedidoRequestDTO requestDTO = new PedidoRequestDTO(5, List.of("Hamburguer"), "Sem cebola");
        Atendente atendente = new Atendente();
        atendente.setNome("Carlos");

        Comida comidaMock = new Comida();
        comidaMock.setNome("Hamburguer");
        comidaMock.setPreco(new BigDecimal("30.00"));

        Pedido pedidoSalvoMock = new Pedido();
        pedidoSalvoMock.setMesa(5);
        pedidoSalvoMock.setAtendente(atendente);

        Mockito.when(atendenteService.buscarEntidadePorEmail(emailLogado)).thenReturn(atendente);
        Mockito.when(pedidoRepository.existsByMesa(5)).thenReturn(false);
        Mockito.when(comidaRepository.findByNomeIgnoreCase("Hamburguer")).thenReturn(Optional.of(comidaMock));
        Mockito.when(pedidoRepository.save(Mockito.any(Pedido.class))).thenReturn(pedidoSalvoMock);
        PedidoResponseDTO response = pedidoService.lancarPedido(requestDTO);

        assertNotNull(response);
        assertEquals(5, response.getMesa());
        Mockito.verify(pedidoRepository, Mockito.times(1)).save(Mockito.any(Pedido.class));
        Mockito.verify(comandaRepository, Mockito.times(1)).save(Mockito.any(Comanda.class));

        SecurityContextHolder.clearContext();
    }

    @Test
    @DisplayName("Lançar exceção se a mesa estiver ocupada")
    void deveLancarExcecaoSeAMesaEstiverOcupada() {
        JWTUserData jwtUserData = new JWTUserData(1L, "admin@rgmail.com", "ADMIN");

        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);

        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        Mockito.when(authentication.getPrincipal()).thenReturn(jwtUserData);
        SecurityContextHolder.setContext(securityContext);
        String emailLogado = jwtUserData.email();

        Comida comidaMock = new Comida();
        comidaMock.setNome("Hamburguer");
        comidaMock.setPreco(new BigDecimal("30.00"));

        PedidoRequestDTO requestDTO = new PedidoRequestDTO(5, List.of("Hamburguer"), "Sem cebola");
        Atendente atendenteMock = new Atendente();
        atendenteMock.setNome("José");

        Pedido pedidoSalvoMock = new Pedido();
        pedidoSalvoMock.setMesa(5);
        pedidoSalvoMock.setAtendente(atendenteMock);


        Mockito.when(atendenteService.buscarEntidadePorEmail(emailLogado)).thenReturn(atendenteMock);
        Mockito.when(pedidoRepository.existsByMesa(5)).thenReturn(true);
        Mockito.when(comidaRepository.findByNomeIgnoreCase("Hamburguer")).thenReturn(Optional.of(comidaMock));
        Mockito.when(pedidoRepository.save(Mockito.any(Pedido.class))).thenReturn(pedidoSalvoMock);

        assertThrows(DataIntegrityViolationException.class, () -> pedidoService.lancarPedido(requestDTO));
        Mockito.verify(pedidoRepository, Mockito.never()).save(Mockito.any(Pedido.class));

        SecurityContextHolder.clearContext();
    }

    @Test
    @DisplayName("Transferir comida entre mesas")
    void deveMoverComidaDeUmaMesaParaOutra() {
        Comida comidaTransferida = new Comida();
        comidaTransferida.setNome("Batata Frita");
        comidaTransferida.setPreco(new BigDecimal("20.00"));

        Pedido pedidoOrigem = new Pedido();
        pedidoOrigem.setMesa(1);
        pedidoOrigem.getComidas().add(comidaTransferida);

        Pedido pedidoDestino = new Pedido();
        pedidoDestino.setMesa(2);

        Mockito.when(pedidoRepository.findByMesa(1)).thenReturn(Optional.of(pedidoOrigem));
        Mockito.when(pedidoRepository.findByMesa(2)).thenReturn(Optional.of(pedidoDestino));
        Mockito.when(pedidoRepository.save(Mockito.any(Pedido.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        pedidoService.transferirComida(1, 2, "Batata Frita");

        Assertions.assertTrue(pedidoOrigem.getComidas().isEmpty());
        Assertions.assertEquals(1, pedidoDestino.getComidas().size());
        Mockito.verify(pedidoRepository, Mockito.times(2)).save(Mockito.any(Pedido.class));
    }
}