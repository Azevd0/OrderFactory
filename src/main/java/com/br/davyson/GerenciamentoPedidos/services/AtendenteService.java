package com.br.davyson.GerenciamentoPedidos.services;

import com.br.davyson.GerenciamentoPedidos.dto.response.AtendenteRegisterResponse;
import com.br.davyson.GerenciamentoPedidos.dto.response.PedidoResponseDTO;
import com.br.davyson.GerenciamentoPedidos.entitys.Atendente;
import com.br.davyson.GerenciamentoPedidos.enums.Role;
import com.br.davyson.GerenciamentoPedidos.exceptions.ObjectNotFoundException;
import com.br.davyson.GerenciamentoPedidos.repositorys.AtendenteRepository;
import com.br.davyson.GerenciamentoPedidos.wrapper.ListWrapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AtendenteService {
    private final AtendenteRepository atendenteRepository;


    public AtendenteService(AtendenteRepository atendenteRepository) {
        this.atendenteRepository = atendenteRepository;
    }

    public Atendente buscarEntidadePorNome(String name){
        return atendenteRepository.findByNomeIgnoreCase(name)
                .orElseThrow(() -> new ObjectNotFoundException("Atendente não encontrado."));
    }
    public Atendente buscarEntidadePorEmail(String email){
        return atendenteRepository.findAtendenteByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException("Usuario não encontrado"));
    }
    @Cacheable(value = "user", key = "'atendente_' + #name")
    public AtendenteRegisterResponse buscarPorNome(String name){
        Atendente atendente = atendenteRepository.findByNomeIgnoreCase(name)
                .orElseThrow(() -> new ObjectNotFoundException("Atendente não encontrado."));
        return new AtendenteRegisterResponse(atendente);
    }
    public ListWrapper<AtendenteRegisterResponse> listarTodosOsAtendentes(){
        List<AtendenteRegisterResponse> listaDeAtendentes = atendenteRepository.findAll()
                .stream().map(AtendenteRegisterResponse::new).collect(Collectors.toList());
        return new ListWrapper<>(listaDeAtendentes);
    }

    public Atendente findById(Long id){
        return atendenteRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Atendente não encontrado com Id "+ id));
    }
    @Cacheable(value = "atendentes", key = "'pedidos_atendente_' + #atendente.id")
    public ListWrapper<PedidoResponseDTO> listarPedidosDoAtendente(Atendente atendente){
        return new ListWrapper<>(
                atendente.getPedidos().stream()
                        .map(PedidoResponseDTO::new)
                        .collect(Collectors.toList())
        );
    }
    @Transactional
    @CacheEvict(value = "user", allEntries = true)
    public AtendenteRegisterResponse registrarAtendente(Atendente atendente){
        if (atendenteRepository.existsByNomeIgnoreCase(atendente.getNome())) {
            throw new DataIntegrityViolationException("Já existe um atendente com esse nome!");
        }
        if(atendenteRepository.existsByEmailIgnoreCase(atendente.getEmail())){
            throw new DataIntegrityViolationException("Este e-mail já está em uso!");
        }
        atendente.setEmail(atendente.getEmail().toLowerCase());
        atendenteRepository.save(atendente);
        return new AtendenteRegisterResponse(atendente);
    }

    @Transactional
    @CacheEvict(value = "user", allEntries = true)
    public AtendenteRegisterResponse promoverAtendente(Long id, Role cargo){
        Atendente atendentePromovido = atendenteRepository.findById(id)
                .orElseThrow(()-> new UsernameNotFoundException("Atentende não encontrado"));
        atendentePromovido.setRole(cargo);
        return new AtendenteRegisterResponse(atendentePromovido);
    }

    @Transactional
    @CacheEvict(value = "user", allEntries = true)
    public void deleteUser(Long id) {
        Atendente atendente = atendenteRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Atendente não encontrado para remoção"));
        atendenteRepository.delete(atendente);
    }
}
