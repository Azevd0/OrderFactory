package com.br.davyson.GerenciamentoPedidos.services;

import com.br.davyson.GerenciamentoPedidos.dto.CategoriaResponseDTO;
import com.br.davyson.GerenciamentoPedidos.entitys.Categoria;
import com.br.davyson.GerenciamentoPedidos.exceptions.ObjectNotFoundException;
import com.br.davyson.GerenciamentoPedidos.repositorys.CategoriaRepository;
import com.br.davyson.GerenciamentoPedidos.repositorys.ComidaRepository;
import com.br.davyson.GerenciamentoPedidos.wrapper.ListWrapper;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final  ComidaRepository comidaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository, ComidaRepository comidaRepository) {
        this.categoriaRepository = categoriaRepository;
        this.comidaRepository = comidaRepository;
    }

    public ListWrapper<CategoriaResponseDTO> ListAll() {
        List<CategoriaResponseDTO> categorias = categoriaRepository.findAll()
                .stream().map(CategoriaResponseDTO::new).toList();
        return new ListWrapper<>(categorias);
    }

    @Transactional
    public CategoriaResponseDTO save(Categoria categoria) {
        if (categoriaRepository.existsByNomeIgnoreCase(categoria.getNome())) {
            throw new DataIntegrityViolationException("A categoria '" + categoria.getNome() + "' já existe!");
        }
        categoriaRepository.save(categoria);
        return new CategoriaResponseDTO(categoria);
    }

    @Transactional
    public CategoriaResponseDTO updateByNome(String nome, Categoria novoNome) {
        Categoria categoria = categoriaRepository.findByNomeIgnoreCase(nome)
                .orElseThrow(() -> new ObjectNotFoundException("Categoria não encontrada: " + nome));
        categoria.setNome(novoNome.getNome());

        categoriaRepository.save(categoria);
        return new CategoriaResponseDTO(categoria);
    }

    @Transactional
    public void deleteByNome(String nome) {
        Categoria categoria = categoriaRepository.findByNomeIgnoreCase(nome)
                .orElseThrow(() -> new ObjectNotFoundException("Categoria '" + nome + "' não encontrada."));

        if (comidaRepository.existsByCategoria(categoria)) {
            throw new DataIntegrityViolationException("Não é possível deletar a categoria '" + nome + "' porque ela possui comidas vinculadas no cardápio.");
        }
        categoriaRepository.delete(categoria);
    }
}

