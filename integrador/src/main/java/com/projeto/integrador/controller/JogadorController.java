package com.projeto.integrador.controller;

import com.projeto.integrador.dto.JogadorDTO;
import com.projeto.integrador.service.JogadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/jogador")
public class JogadorController {

    @Autowired
    private JogadorService jogadorService;

    // Método POST - Criar jogador
    @PostMapping("/adicionar")
    public ResponseEntity<Object> createUser(@Valid @RequestBody JogadorDTO jogadorDTO) {
        try {
            JogadorDTO novoJogador = jogadorService.createUser(jogadorDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoJogador);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao cadastrar jogador");
        }
    }

    // Método GET - Obter todos os jogadores
    @GetMapping
    public ResponseEntity<List<JogadorDTO>> getAllUsers() {
        List<JogadorDTO> jogadores = jogadorService.getAllJogadores();
        return ResponseEntity.ok(jogadores);
    }

    // Método PUT - Atualizar jogador
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable Long id, @Valid @RequestBody JogadorDTO jogadorDTO) {
        JogadorDTO jogadorAtualizado = jogadorService.updateUser(id, jogadorDTO);
        
        if (jogadorAtualizado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Jogador não encontrado");
        }

        return ResponseEntity.ok(jogadorAtualizado);  // Retorna o jogador atualizado
    }

    // Método DELETE - Deletar jogador por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
        boolean deleted = jogadorService.deleteUser(id);
        
        if (deleted) {
            return ResponseEntity.noContent().build(); // Retorna 204 (Sem conteúdo)
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Jogador não encontrado");
    }
}
