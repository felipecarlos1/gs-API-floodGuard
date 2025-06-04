package br.fiap.dtos;


import java.time.LocalDateTime;
import java.util.Objects;

public class UsuarioResponseDTO {

    private int id;
    private String nome;
    private String email;
    private String tipoUsuario;
    private String cidade;
    private String bairro;
    private LocalDateTime criadoEm;

    public UsuarioResponseDTO() {
    }

    public UsuarioResponseDTO(int id, String nome, String email, String tipoUsuario, String cidade, String bairro, LocalDateTime criadoEm) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.tipoUsuario = tipoUsuario;
        this.cidade = cidade;
        this.bairro = bairro;
        this.criadoEm = criadoEm;
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

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
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

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioResponseDTO that = (UsuarioResponseDTO) o;
        return id == that.id && Objects.equals(nome, that.nome) && Objects.equals(email, that.email) && Objects.equals(tipoUsuario, that.tipoUsuario) && Objects.equals(cidade, that.cidade) && Objects.equals(bairro, that.bairro) && Objects.equals(criadoEm, that.criadoEm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, email, tipoUsuario, cidade, bairro, criadoEm);
    }

    @Override
    public String toString() {
        return "UsuarioResponseDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", tipoUsuario='" + tipoUsuario + '\'' +
                ", cidade='" + cidade + '\'' +
                ", bairro='" + bairro + '\'' +
                ", criadoEm=" + criadoEm +
                '}';
    }
}
