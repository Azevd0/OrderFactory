package com.br.davyson.GerenciamentoPedidos.services;

import com.br.davyson.GerenciamentoPedidos.dto.AtendenteRequestDTO;
import com.br.davyson.GerenciamentoPedidos.dto.AtendenteResponseDTO;
import com.br.davyson.GerenciamentoPedidos.dto.PedidoResponseDTO;
import com.br.davyson.GerenciamentoPedidos.entitys.Atendente;
import com.br.davyson.GerenciamentoPedidos.exceptions.ObjectNotFoundException;
import com.br.davyson.GerenciamentoPedidos.repositorys.AtendenteRepository;
import com.br.davyson.GerenciamentoPedidos.wrapper.ListWrapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AtendenteService {
    private final AtendenteRepository atendenteRepository;


    public AtendenteService(AtendenteRepository atendenteRepository) {
        this.atendenteRepository = atendenteRepository;
    }

    public ListWrapper<AtendenteResponseDTO> listAll(){
        List<AtendenteResponseDTO> atendentes = atendenteRepository.findAll()
                .stream().map(AtendenteResponseDTO::new).toList();
        return new ListWrapper<>(atendentes);
    }

    public Atendente buscarEntidadePorNome(String name){
        return atendenteRepository.findByNomeIgnoreCase(name)
                .orElseThrow(() -> new ObjectNotFoundException("Atendente não encontrado."));
    }
    public AtendenteResponseDTO buscarPorNome(String name){
        Atendente atendente = atendenteRepository.findByNomeIgnoreCase(name)
                .orElseThrow(() -> new ObjectNotFoundException("Atendente não encontrado."));
        return new AtendenteResponseDTO(atendente);
    }

    public Atendente findById(Long id){
        return atendenteRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Atendente não encontrado com Id "+ id));
    }
    public ListWrapper<PedidoResponseDTO> listarPedidosDoAtendente(Atendente atendente){
        List<PedidoResponseDTO> atendentePedidos = atendente.getPedidos().stream().map(PedidoResponseDTO::new).toList();
        return new ListWrapper<>(atendentePedidos);
    }
    @Transactional
    public AtendenteResponseDTO saveAtendente(Atendente atendente){
        if (atendenteRepository.existsByNomeIgnoreCase(atendente.getNome())) {
            throw new DataIntegrityViolationException("Já existe um atendente com esse nome!");
        }
        atendenteRepository.save(atendente);
        return new AtendenteResponseDTO(atendente);
    }

    @Transactional
    public AtendenteResponseDTO updateAtendenteByName(String name, AtendenteRequestDTO dto) {
        Atendente atendenteExistente = buscarEntidadePorNome(name);

        if (dto.nome() != null) atendenteExistente.setNome(dto.nome());
        if (dto.senha() != null) atendenteExistente.setSenha(dto.senha());

        atendenteRepository.save(atendenteExistente);
        return new AtendenteResponseDTO(atendenteExistente);
    }

    @Transactional
    public void deleteUser(String name) {
        Atendente atendente = buscarEntidadePorNome(name);
        atendenteRepository.delete(atendente);
    }
}
