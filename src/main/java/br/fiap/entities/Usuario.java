package br.fiap.entities;

import java.time.LocalDateTime;
import java.util.Objects;

public class Usuario {

    private int id;
    private String nome;
    private String email;
    private String senhaHash;
    private String tipoUsuario;
    private LocalDateTime criadoEm;
    private String cidade;
    private String bairro;

    public Usuario() {
    }

    public Usuario(int id, String nome, String email, String senhaHash, String tipoUsuario, LocalDateTime criadoEm, String cidade, String bairro) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senhaHash = senhaHash;
        this.tipoUsuario = tipoUsuario;
        this.criadoEm = criadoEm;
        this.cidade = cidade;
        this.bairro = bairro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenhaHash() {
        return senhaHash;
    }

    public void setSenhaHash(String senhaHash) {
        this.senhaHash = senhaHash;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return id == usuario.id && Objects.equals(nome, usuario.nome) && Objects.equals(email, usuario.email) && Objects.equals(senhaHash, usuario.senhaHash) && Objects.equals(tipoUsuario, usuario.tipoUsuario) && Objects.equals(criadoEm, usuario.criadoEm) && Objects.equals(cidade, usuario.cidade) && Objects.equals(bairro, usuario.bairro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, email, senhaHash, tipoUsuario, criadoEm, cidade, bairro);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senhaHash='" + senhaHash + '\'' +
                ", tipoUsuario='" + tipoUsuario + '\'' +
                ", criadoEm=" + criadoEm +
                ", cidade='" + cidade + '\'' +
                ", bairro='" + bairro + '\'' +
                '}';
    }
}
