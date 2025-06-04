package br.fiap.dtos;

import java.util.Objects;

public class DadosClimaticosDTO {

    private double temperatura;
    private double umidade;
    private double pressao;
    private String descricao;
    private double nivelRioEstimado;

    public DadosClimaticosDTO() {
    }

    public DadosClimaticosDTO(double temperatura, double umidade, double pressao, String descricao, double nivelRioEstimado) {
        this.temperatura = temperatura;
        this.umidade = umidade;
        this.pressao = pressao;
        this.descricao = descricao;
        this.nivelRioEstimado = nivelRioEstimado;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public double getUmidade() {
        return umidade;
    }

    public void setUmidade(double umidade) {
        this.umidade = umidade;
    }

    public double getPressao() {
        return pressao;
    }

    public void setPressao(double pressao) {
        this.pressao = pressao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getNivelRioEstimado() {
        return nivelRioEstimado;
    }

    public void setNivelRioEstimado(double nivelRioEstimado) {
        this.nivelRioEstimado = nivelRioEstimado;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DadosClimaticosDTO that = (DadosClimaticosDTO) o;
        return Double.compare(temperatura, that.temperatura) == 0 && Double.compare(umidade, that.umidade) == 0 && Double.compare(pressao, that.pressao) == 0 && Double.compare(nivelRioEstimado, that.nivelRioEstimado) == 0 && Objects.equals(descricao, that.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(temperatura, umidade, pressao, descricao, nivelRioEstimado);
    }

    @Override
    public String toString() {
        return "DadosClimaticosDTO{" +
                "temperatura=" + temperatura +
                ", umidade=" + umidade +
                ", pressao=" + pressao +
                ", descricao='" + descricao + '\'' +
                ", nivelRioEstimado=" + nivelRioEstimado +
                '}';
    }
}
