package com.br.davyson.GerenciamentoPedidos.controllers;
import com.br.davyson.GerenciamentoPedidos.dto.PedidoResponseDTO;
import com.br.davyson.GerenciamentoPedidos.entitys.Atendente;
import com.br.davyson.GerenciamentoPedidos.services.AtendenteService;
import com.br.davyson.GerenciamentoPedidos.dto.AtendenteResponseDTO;
import com.br.davyson.GerenciamentoPedidos.dto.AtendenteRequestDTO;
import com.br.davyson.GerenciamentoPedidos.wrapper.ListWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/atendente")
@Tag(name = "Atendentes", description = "Gerenciamento dos funcionários")

public class AtendenteController {

    private final AtendenteService atendenteService;

    public AtendenteController(AtendenteService atendenteService) {
        this.atendenteService = atendenteService;
    }

    @Operation(summary = "Listar todos os atendentes")
    @GetMapping
    public ResponseEntity<ListWrapper<AtendenteResponseDTO>> listarTodos() {
        ListWrapper<AtendenteResponseDTO> list = atendenteService.listAll();
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Buscar atendente por nome")
    @GetMapping("/busca/{nome}")
    public ResponseEntity<AtendenteResponseDTO> buscarPorNome(@PathVariable String nome) {
        return ResponseEntity.ok(atendenteService.buscarPorNome(nome));
    }
    @Operation(summary = "Listar pedidos do atendente pelo seu id")
    @GetMapping("/listarPedidos/{id}")
    public ResponseEntity<ListWrapper<PedidoResponseDTO>> listarPedidosDoAtendente(@PathVariable Long id){
        Atendente atendete = atendenteService.findById(id);
        ListWrapper<PedidoResponseDTO> pedidos = atendenteService.listarPedidosDoAtendente(atendete);
        return ResponseEntity.ok(pedidos);
    }
    @Operation(summary = "Cadastrar atendente")
    @PostMapping("/cadastro")
    public ResponseEntity<AtendenteResponseDTO> cadastrar(@RequestBody @Valid AtendenteRequestDTO dto) {
        Atendente atendente = new Atendente();
        atendente.setNome(dto.nome());
        atendente.setLogin(dto.login());
        atendente.setSenha(dto.senha());

        AtendenteResponseDTO salvo = atendenteService.saveAtendente(atendente);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @Operation(summary = "Atualizar atendente")
    @PutMapping("/atualizacao/{nome}")
    public ResponseEntity<AtendenteResponseDTO> atualizar(@PathVariable String nome, @RequestBody @Valid AtendenteRequestDTO dto) {
        AtendenteResponseDTO atualizado = atendenteService.updateAtendenteByName(nome, dto);
        return ResponseEntity.ok(atualizado);
    }

    @Operation(summary = "Remover atendente")
    @DeleteMapping("/remover/{nome}")
    public ResponseEntity<Void> deletar(@PathVariable String nome) {
        atendenteService.deleteUser(nome);
        return ResponseEntity.noContent().build();
    }
}
