package br.com.estudo.aluraspring.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensagem;
    @ManyToOne
    private Topico topico;
    private LocalDateTime dataCriacao = LocalDateTime.now();
    @ManyToOne
    private Usuario autor;
    private Boolean solucao = false;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resposta resposta = (Resposta) o;
        return Objects.equals(id, resposta.id) && Objects.equals(mensagem, resposta.mensagem) && Objects.equals(topico, resposta.topico) && Objects.equals(dataCriacao, resposta.dataCriacao) && Objects.equals(autor, resposta.autor) && Objects.equals(solucao, resposta.solucao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mensagem, topico, dataCriacao, autor, solucao);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Topico getTopico() {
        return topico;
    }

    public void setTopico(Topico topico) {
        this.topico = topico;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public Boolean getSolucao() {
        return solucao;
    }

    public void setSolucao(Boolean solucao) {
        this.solucao = solucao;
    }
}
