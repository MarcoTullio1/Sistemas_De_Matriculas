package dominio;

import dominio.interfaces.Observer;

public class Financeiro implements Observer {

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
