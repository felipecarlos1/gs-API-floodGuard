package br.fiap.entities;

import java.time.LocalDateTime;
import java.util.Objects;

public class LeituraNivel {

    private int id;
    private LocalDateTime dataHora;
    private String localizacao;
    private double nivel;
    private String fonte;

    public LeituraNivel() {
    }

    public LeituraNivel(int id, LocalDateTime dataHora, String localizacao, double nivel, String fonte) {
        this.id = id;
        this.dataHora = dataHora;
        this.localizacao = localizacao;
        this.nivel = nivel;
        this.fonte = fonte;
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

    public double getNivel() {
        return nivel;
    }

    public void setNivel(double nivel) {
        this.nivel = nivel;
    }

    public String getFonte() {
        return fonte;
    }

    public void setFonte(String fonte) {
        this.fonte = fonte;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LeituraNivel that = (LeituraNivel) o;
        return id == that.id && Double.compare(nivel, that.nivel) == 0 && Objects.equals(dataHora, that.dataHora) && Objects.equals(localizacao, that.localizacao) && Objects.equals(fonte, that.fonte);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dataHora, localizacao, nivel, fonte);
    }

    @Override
    public String toString() {
        return "LeituraNivel{" +
                "id=" + id +
                ", dataHora=" + dataHora +
                ", localizacao='" + localizacao + '\'' +
                ", nivel=" + nivel +
                ", fonte='" + fonte + '\'' +
                '}';
    }
}
