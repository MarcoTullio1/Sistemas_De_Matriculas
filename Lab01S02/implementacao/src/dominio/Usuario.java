package dominio;

import java.io.Serializable;
import java.util.Objects;

public abstract class Usuario implements Serializable {
    private static final long serialVersionUID = 523521873160625L;
    private String nome;
    private String senha;

    public Usuario(String nome, String senha){
        this.nome = nome;
        this.senha = senha;
    }

    protected Usuario() {
    }

    protected boolean logar() {
        return true;
    }

    public String getNome(){
        return this.nome;
    }

    protected String getSenha(){
        return this.senha;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        Usuario usuario = (Usuario) o;
        return getNome().equals(usuario.getNome()) && getSenha().equals(usuario.getSenha());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNome(), getSenha());
    }
}
