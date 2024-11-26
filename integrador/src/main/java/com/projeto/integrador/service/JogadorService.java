package com.projeto.integrador.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.integrador.dto.JogadorDTO;
import com.projeto.integrador.model.Jogador;
import com.projeto.integrador.repository.JogadorRepository;

@Service
public class JogadorService {

    @Autowired
    private JogadorRepository jogadorRepository; // Repositório para acessar os dados dos jogadores

    // Método para buscar todos os jogadores
    public List<JogadorDTO> getAllJogadores() {
        List<Jogador> jogadores = jogadorRepository.findAll();
        return jogadores.stream()
                .map(JogadorDTO::new)  // Converte cada Jogador em JogadorDTO
                .collect(Collectors.toList());
    }

    // Método para criar um novo jogador
    public JogadorDTO createUser(JogadorDTO jogadorDTO) {
        Jogador jogador = new Jogador(jogadorDTO);
        jogador.setNome(jogadorDTO.getNome());
        jogador.setIdade(jogadorDTO.getIdade());
        jogador.setTime(jogadorDTO.getTime());
        jogador.setSelecao(jogadorDTO.getSelecao());
        jogador.setNumeroCamisa(jogadorDTO.getCamisa());

        Jogador jogadorCriado = jogadorRepository.save(jogador);

        return new JogadorDTO(jogadorCriado);
    }

    public JogadorDTO updateUser(Long id, JogadorDTO jogadorDTO) {
        Optional<Jogador> jogadorExistente = jogadorRepository.findById(id);
        
        if (jogadorExistente.isPresent()) {
            Jogador jogador = jogadorExistente.get();
            jogador.setNome(jogadorDTO.getNome());
            jogador.setIdade(jogadorDTO.getIdade());
            jogador.setTime(jogadorDTO.getTime());
            jogador.setSelecao(jogadorDTO.getSelecao());
            jogador.setNumeroCamisa(jogadorDTO.getCamisa());
            
            Jogador jogadorAtualizado = jogadorRepository.save(jogador);
            return new JogadorDTO(jogadorAtualizado);
        }
        return null;  // Caso o jogador não seja encontrado, retorna null
    }

    // Método para excluir um jogador
    public boolean deleteUser(Long id) {
        if (jogadorRepository.existsById(id)) {
            jogadorRepository.deleteById(id);
            return true;  // Jogador deletado com sucesso
        }
        return false;  // Jogador não encontrado
    }


    // Método para buscar jogador por ID
    public JogadorDTO getJogadorById(Long id) {
        Optional<Jogador> jogador = jogadorRepository.findById(id);
        return jogador.map(JogadorDTO::new).orElse(null);
    }
}
