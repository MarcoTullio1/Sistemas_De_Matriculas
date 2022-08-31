package dominio;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Professor extends Usuario implements Serializable {
    private static final long serialVersionUID = 5235234185360625L;
    private Set<Disciplina> disciplinas;

    public Professor(String nome, String senha){
        super(nome, senha);
        disciplinas = new HashSet<>();
    }

    public String verAlunosMatriculados(Disciplina disciplina){
        StringBuilder sb = new StringBuilder();
        sb.append(disciplina.getNome() + "\t"+ disciplina.getTipo().name() + "\n");
        disciplina.getAlunosMatriculados().forEach(aluno -> sb.append("- " +aluno.getNome() + "\n"));
        return sb.toString();
    }

    public Set<Disciplina> getDisciplinas(){
        return this.disciplinas;
    }
}
