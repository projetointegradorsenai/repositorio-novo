package com.projeto.integrador.dto;

import com.projeto.integrador.model.Jogador;

public class JogadorDTO {

    private Long id;  // Adicionando o ID, pois é importante para edição e exclusão
    private String nome;
    private int idade;
    private String time;
    private String selecao;
    private int camisa;

    // Construtores
    public JogadorDTO() {}

    // Construtor que recebe o Jogador do banco de dados
    public JogadorDTO(Jogador jogador) {
        this.id = jogador.getId();
        this.nome = jogador.getNome();
        this.idade = jogador.getIdade();
        this.time = jogador.getTime();
        this.selecao = jogador.getSelecao();
        this.camisa = jogador.getNumeroCamisa();
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSelecao() {
        return selecao;
    }

    public void setSelecao(String selecao) {
        this.selecao = selecao;
    }

    public int getCamisa() {
        return camisa;
    }

    public void setCamisa(int camisa) {
        this.camisa = camisa;
    }
}
 