package dominio;

public abstract class Usuario {
    private String nome;
    private String senha;

    public Usuario(String nome){
        this.nome = nome;
    }

    public abstract void cadastrar();

    protected boolean logar() {
        return true;
    }

    public String getNome(){
        return this.nome;
    }
}
