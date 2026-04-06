package com.br.davyson.GerenciamentoPedidos.services;

import com.br.davyson.GerenciamentoPedidos.dto.ComidaRequestDTO;
import com.br.davyson.GerenciamentoPedidos.dto.ComidaResponseDTO;
import com.br.davyson.GerenciamentoPedidos.entitys.Categoria;
import com.br.davyson.GerenciamentoPedidos.entitys.Comida;
import com.br.davyson.GerenciamentoPedidos.exceptions.ObjectNotFoundException;
import com.br.davyson.GerenciamentoPedidos.repositorys.CategoriaRepository;
import com.br.davyson.GerenciamentoPedidos.repositorys.ComidaRepository;
import com.br.davyson.GerenciamentoPedidos.wrapper.ListWrapper;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ComidaService {
    private final ComidaRepository comidaRepository;
    private final CategoriaRepository categoriaRepository;

    public ComidaService(ComidaRepository comidaRepository, CategoriaRepository categoriaRepository) {
        this.comidaRepository = comidaRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public ListWrapper<ComidaResponseDTO> openMenu(){
        List<ComidaResponseDTO> cardapio = comidaRepository.showMenu().stream().map(ComidaResponseDTO::new).toList();
        return new ListWrapper<>(cardapio);
    }

    public ListWrapper<ComidaResponseDTO> findComida(String nome) {
        List<ComidaResponseDTO> comida = comidaRepository.findByNomeContainingIgnoreCase(nome).stream()
                .map(ComidaResponseDTO::new)
                .toList();

        if (comida.isEmpty()) {
            throw new ObjectNotFoundException("Nenhuma comida encontrada com o termo: " + nome);
        }
        return new ListWrapper<>(comida);
    }
    public Comida findComidaByName(String nome) {
        return comidaRepository.findByNomeIgnoreCase(nome)
                .orElseThrow(() -> new ObjectNotFoundException("Comida não encontrada."));
    }

    public ListWrapper<ComidaResponseDTO> findComidaByCategoria(String nome){
        List<ComidaResponseDTO> foodList = comidaRepository.findByCategoriaNomeIgnoreCase(nome)
                .stream()
                .map(ComidaResponseDTO::new)
                .toList();
        if(foodList.isEmpty()){
            throw new ObjectNotFoundException("Categoria " +nome+ " não existe");
        }
        return new ListWrapper<>(foodList);
    }
    @Transactional
    public ComidaResponseDTO saveFood(Comida comida, String categoriaNome) {
        Categoria categoriaExistente = categoriaRepository.findByNomeIgnoreCase(categoriaNome)
                .orElseThrow(() -> new ObjectNotFoundException(
                        "Não foi possível cadastrar: A categoria '" + categoriaNome + "' não foi encontrada!"));
        if (comidaRepository.existsByNomeIgnoreCase(comida.getNome())) {
            throw new DataIntegrityViolationException("A comida '" + comida.getNome() + "' já existe!");
        }
        comida.setCategoria(categoriaExistente);
        comidaRepository.save(comida);
        return new ComidaResponseDTO(comida);
    }

    @Transactional
    public ComidaResponseDTO updateFood(String nome, ComidaRequestDTO comidaAtualizada) {
        Comida comidaExistente = comidaRepository.findByNomeIgnoreCase(nome)
                .orElseThrow(() -> new ObjectNotFoundException("Comida '" + nome + "' não encontrada para atualização."));

        if (comidaAtualizada.nome() != null) comidaExistente.setNome(comidaAtualizada.nome());
        if (comidaAtualizada.descricao() != null) comidaExistente.setDescricao(comidaAtualizada.descricao());
        if (comidaAtualizada.preco() != null) comidaExistente.setPreco(comidaAtualizada.preco());

       comidaRepository.save(comidaExistente);
       return new ComidaResponseDTO(comidaExistente);
    }
    @Transactional
    public void deleteFood(String nome) {
        Comida comida = comidaRepository.findByNomeIgnoreCase(nome)
                .orElseThrow(() -> new ObjectNotFoundException("Não foi possível deletar: Comida '" + nome + "' não encontrada."));

        comidaRepository.delete(comida);
    }
}
