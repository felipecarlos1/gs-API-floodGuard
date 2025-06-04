package br.fiap.entities;

import java.util.Objects;

public class ConfiguracaoUsuario {

    private int id;
    private int usuarioId;
    private boolean receberAlertasEmail;
    private String idioma;

    public ConfiguracaoUsuario() {
    }

    public ConfiguracaoUsuario(int id, int usuarioId, boolean receberAlertasEmail, String idioma) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.receberAlertasEmail = receberAlertasEmail;
        this.idioma = idioma;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public boolean isReceberAlertasEmail() {
        return receberAlertasEmail;
    }

    public void setReceberAlertasEmail(boolean receberAlertasEmail) {
        this.receberAlertasEmail = receberAlertasEmail;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ConfiguracaoUsuario that = (ConfiguracaoUsuario) o;
        return id == that.id && usuarioId == that.usuarioId && receberAlertasEmail == that.receberAlertasEmail && Objects.equals(idioma, that.idioma);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, usuarioId, receberAlertasEmail, idioma);
    }

    @Override
    public String toString() {
        return "ConfiguracaoUsuario{" +
                "id=" + id +
                ", usuarioId=" + usuarioId +
                ", receberAlertasEmail=" + receberAlertasEmail +
                ", idioma='" + idioma + '\'' +
                '}';
    }
}
