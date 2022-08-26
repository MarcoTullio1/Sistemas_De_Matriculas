package dominio;

import java.util.List;

public class Secretaria {
    private List<Professor> professors;
    private List<Aluno> alunos;
    private List<Disciplina> disciplinas;

    public Secretaria(List<Professor> professors, List<Aluno> alunos, List<Disciplina> disciplinas){
        this.alunos = alunos;
        this.disciplinas = disciplinas;
        this.professors = professors;
    }

    public void gerarCurriculo(Curso curso, Disciplina[] disciplinas) {
        //atualizar o arquivo do curso com as disciplinas.

    }

    public boolean logar() {
        return true;
    }
}
