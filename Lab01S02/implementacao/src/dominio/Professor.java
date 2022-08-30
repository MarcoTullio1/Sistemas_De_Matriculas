package dominio;

import java.io.Serializable;
import java.util.List;

public class Professor extends Usuario implements Serializable {
    private static final long serialVersionUID = 5235234185360625L;
    private List<Disciplina> disciplinas;

    public Professor(String nome, String senha){
        super(nome, senha);
    }

    public String verAlunosMatriculados(Disciplina disciplina){
        StringBuilder sb = new StringBuilder();
        sb.append(disciplina.getNome() + "\t"+ disciplina.getTipo().name() + "\n\n");
        disciplina.getAlunosMatriculados().stream().forEach(aluno -> sb.append("- " +aluno.getNome() + "\n"));
        return sb.toString();
    }

    public List<Disciplina> getDisciplinas(){
        return this.disciplinas;
    }
}
