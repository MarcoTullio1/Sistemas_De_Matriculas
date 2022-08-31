public abstract class Usuario {
    protected String nome;
    protected String senha;

    public Usuario(String nome){
        this.nome = nome;
    }

    protected void cadastrar() {
    }

    protected boolean logar() {
        return true;
    }
}
