package dominio;

import dominio.interfaces.Observer;

import java.io.Serializable;

public class Financeiro implements Observer, Serializable {
    private static final long serialVersionUID = 52541928360625L;

    Aluno aluno;

    public Financeiro(){};

    private void gerarCobranca() {
        System.out.println(aluno.getNome() + " foi cobrado!");
    }

    public void setAluno(Aluno aluno){
        this.aluno = aluno;
    }

    @Override
    public void update() {
        gerarCobranca();
    }
}
