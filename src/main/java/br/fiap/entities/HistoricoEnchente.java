package br.fiap.entities;

import java.time.LocalDateTime;
import java.util.Objects;

public class HistoricoEnchente {

    private int id;
    private LocalDateTime dataHora;
    private String localizacao;
    private double nivelMaximo;
    private String descricao;

    public HistoricoEnchente() {
    }

    public HistoricoEnchente(int id, LocalDateTime dataHora, String localizacao, double nivelMaximo, String descricao) {
        this.id = id;
        this.dataHora = dataHora;
        this.localizacao = localizacao;
        this.nivelMaximo = nivelMaximo;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public double getNivelMaximo() {
        return nivelMaximo;
    }

    public void setNivelMaximo(double nivelMaximo) {
        this.nivelMaximo = nivelMaximo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        HistoricoEnchente that = (HistoricoEnchente) o;
        return id == that.id && Double.compare(nivelMaximo, that.nivelMaximo) == 0 && Objects.equals(dataHora, that.dataHora) && Objects.equals(localizacao, that.localizacao) && Objects.equals(descricao, that.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dataHora, localizacao, nivelMaximo, descricao);
    }

    @Override
    public String toString() {
        return "HistoricoEnchente{" +
                "id=" + id +
                ", dataHora=" + dataHora +
                ", localizacao='" + localizacao + '\'' +
                ", nivelMaximo=" + nivelMaximo +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
