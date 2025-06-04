package br.fiap.entities;

import java.time.LocalDateTime;
import java.util.Objects;

public class Alerta {

    private int id;
    private String tipoAlerta;
    private String descricao;
    private LocalDateTime dataHora;
    private String localizacao;
    private String gravidade;
    private int geradoPor; // referência ao ID do usuário

    public Alerta() {
    }

    public Alerta(int id, String tipoAlerta, String descricao, LocalDateTime dataHora, String localizacao, String gravidade, int geradoPor) {
        this.id = id;
        this.tipoAlerta = tipoAlerta;
        this.descricao = descricao;
        this.dataHora = dataHora;
        this.localizacao = localizacao;
        this.gravidade = gravidade;
        this.geradoPor = geradoPor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipoAlerta() {
        return tipoAlerta;
    }

    public void setTipoAlerta(String tipoAlerta) {
        this.tipoAlerta = tipoAlerta;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public String getGravidade() {
        return gravidade;
    }

    public void setGravidade(String gravidade) {
        this.gravidade = gravidade;
    }

    public int getGeradoPor() {
        return geradoPor;
    }

    public void setGeradoPor(int geradoPor) {
        this.geradoPor = geradoPor;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Alerta alerta = (Alerta) o;
        return id == alerta.id && geradoPor == alerta.geradoPor && Objects.equals(tipoAlerta, alerta.tipoAlerta) && Objects.equals(descricao, alerta.descricao) && Objects.equals(dataHora, alerta.dataHora) && Objects.equals(localizacao, alerta.localizacao) && Objects.equals(gravidade, alerta.gravidade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tipoAlerta, descricao, dataHora, localizacao, gravidade, geradoPor);
    }

    @Override
    public String toString() {
        return "Alerta{" +
                "id=" + id +
                ", tipoAlerta='" + tipoAlerta + '\'' +
                ", descricao='" + descricao + '\'' +
                ", dataHora=" + dataHora +
                ", localizacao='" + localizacao + '\'' +
                ", gravidade='" + gravidade + '\'' +
                ", geradoPor=" + geradoPor +
                '}';
    }
}
