package dominio;

import config.Config;
import dominio.enums.TipoDisciplina;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Disciplina implements Serializable {
    private static final long serialVersionUID = 1256421523585360625L;


    private static final int MAX_ALUNOS = Config.getMaxAlunosPorDisciplina();
    private String nome;

    protected TipoDisciplina tipo;

    protected Set<Aluno> alunosMatriculados;

    public Disciplina(String nome, TipoDisciplina tipo) {
        this.nome = nome;
        this.alunosMatriculados = new HashSet<>();
        this.tipo = tipo;
    }

    public void matricularAluno(Aluno novo) throws Exception{
        if(novo.getQtdDisciplinasPorTipo(this.tipo) < this.tipo.getQuantidadeMax() && this.getAlunosMatriculados().size() < MAX_ALUNOS){
            this.alunosMatriculados.add(novo);
            novo.getDisciplinas().add(this);
            return;
        }
        throw new Exception("Houve um erro ao matricular o aluno " + novo.getNome());
    }
    public void removerMatricula(Aluno novo) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        Disciplina that = (Disciplina) o;
        return getNome().equals(that.getNome());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNome());
    }
}
