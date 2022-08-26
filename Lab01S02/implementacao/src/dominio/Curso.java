package dominio;

import java.util.HashSet;
import java.util.Set;

public class Curso {
    private String nome;
    private int creditos;
    private Set<Disciplina> disciplinas;

    public Curso(String nome, int creditos) {
        this.nome = nome;
        this.creditos = creditos;
        this.disciplinas = new HashSet<>();
    }

    public void addDisciplina(Disciplina disciplina) throws Exception{
        if(this.disciplinas.add(disciplina)) return;
        throw new Exception("Houve um erro ao adicionar a disciplina " + disciplina.getNome() );
    }

    public void atualizarCurso() {
        //Ler do arquivo do curso as disciplinas.
    }
}
