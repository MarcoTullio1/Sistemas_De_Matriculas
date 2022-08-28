package dominio;

import dominio.enums.TipoDisciplina;
import dominio.interfaces.Observable;
import dominio.interfaces.Observer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Aluno extends Usuario implements Observable, Serializable {
    private static final long serialVersionUID = 2806421523585360625L;

    private Set<Disciplina> disciplinas;
    private List<Observer> observers;

    public Aluno(String nome, String senha,Observer observer){
        super(nome, senha);
        this.disciplinas = new HashSet<>();
        this.observers = new ArrayList<>();
        attach(observer);
    }
    public Aluno(String nome, String senha){
        super(nome, senha);
        this.disciplinas = new HashSet<>();
        this.observers = new ArrayList<>();
    }

    public Set<Disciplina> getDisciplinas(){
        return this.disciplinas;
    }


    public int getQtdDisciplinasPorTipo(TipoDisciplina tipo){
        return (int) disciplinas.stream()
                .filter(disciplina -> disciplina.getTipo().getQuantidadeMax() == tipo.getQuantidadeMax())
                .count();
    }

    @Override
    public void attach(Observer observer) {
      observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(Observer::update);
    }

}
