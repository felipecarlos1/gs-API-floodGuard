package br.fiap.dtos;

import java.util.Objects;

public class UsuarioLoginDTO {

    private String email;
    private String senha;

    public UsuarioLoginDTO() {
    }

    public UsuarioLoginDTO(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioLoginDTO that = (UsuarioLoginDTO) o;
        return Objects.equals(email, that.email) && Objects.equals(senha, that.senha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, senha);
    }

    @Override
    public String toString() {
        return "UsuarioLoginDTO{" +
                "email='" + email + '\'' +
                ", senha='**********'" +
                '}';
    }
}
