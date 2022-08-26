package dominio;

import config.Config;
import dominio.enums.TipoDisciplina;

import java.util.HashSet;
import java.util.Set;

public class Disciplina {
    private static final int MAX_ALUNOS = Config.getMaxAlunosPorDisciplina();
    private String nome;
    private double preco;

    protected TipoDisciplina tipo;

    protected Set<Aluno> alunosMatriculados;

    public Disciplina(String nome, double preco, TipoDisciplina tipo) {
        this.nome = nome;
        this.preco = preco;
        this.alunosMatriculados = new HashSet<>();
        this.tipo = tipo;
    }

    public void matricularAluno(Aluno novo) throws Exception{
        if(novo.getQtdDisciplinasPorTipo(this.tipo) < this.tipo.getQuantidadeMax()){
            this.alunosMatriculados.add(novo);
            novo.getDisciplinas().add(this);
            return;
        }
        throw new Exception("Houve um erro ao matricular o aluno " + novo.getNome());
    }
    public void removerMatricula(Aluno novo) throws Exception{
        if(alunosMatriculados.remove(novo)){
            novo.getDisciplinas().remove(this);
            return;
        }
        throw new NullPointerException("Aluno não encontrado!");
    };

    public TipoDisciplina getTipo(){
        return this.tipo;
    }

    public boolean isAtiva() {
        return alunosMatriculados.size() <= 3 ? false : true;
    }

    public Set<Aluno> getAlunosMatriculados(){
        return this.alunosMatriculados;
    }

    public String getNome(){
        return this.nome;
    }
}
