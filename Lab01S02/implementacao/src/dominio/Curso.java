package dominio;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Curso implements Serializable {
    private static final long serialVersionUID = 2806431254625L;
    private String nome;
    private int creditos;
    private Set<Disciplina> disciplinas;

    public Curso(String nome) {
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

    public void setCreditos(int creditos){
        this.creditos = creditos;
    }

    public String getNome(){
        return this.nome;
    }
}
