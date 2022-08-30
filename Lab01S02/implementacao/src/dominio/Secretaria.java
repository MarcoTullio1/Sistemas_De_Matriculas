package dominio;

import java.util.Set;

public class Secretaria {
    private Set<Professor> professors;
    private Set<Aluno> alunos;
    private Set<Disciplina> disciplinas;

    public Secretaria(Set<Professor> professors, Set<Aluno> alunos, Set<Disciplina> disciplinas){
        this.alunos = alunos;
        this.disciplinas = disciplinas;
        this.professors = professors;
    }

    public boolean logar() {
        return true;
    }

    public Set<Professor> getProfessors() {
        return professors;
    }

    public void addProfessor(Professor professor) throws Exception{
        if(professors.add(professor)) return;
        throw new Exception("Professor já cadastrado!");
    }

    public Set<Aluno> getAlunos() {
        return alunos;
    }

    public void addAluno(Aluno aluno) throws Exception{
        if(alunos.add(aluno)) return;
        throw new Exception("Aluno já cadastrado!");
    }

    public Set<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void addDisciplina(Disciplina disciplina) throws Exception{
        if(disciplinas.add(disciplina)) return;
        throw new Exception("Disciplina já cadastrada!");
    }

}
