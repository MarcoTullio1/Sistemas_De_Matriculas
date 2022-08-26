package dominio;

import dominio.enums.TipoDisciplina;
import dominio.interfaces.Observable;
import dominio.interfaces.Observer;

import java.util.ArrayList;
import java.util.List;

public class Aluno extends Usuario implements Observable {
    private List<Disciplina> disciplinas;
    private List<Observer> observers;

    public Aluno(String nome, Observer observer){
        super(nome);
        this.disciplinas = new ArrayList<>();
    }

    public List<Disciplina> getDisciplinas(){
        return this.disciplinas;
    }


    public int getQtdDisciplinasPorTipo(TipoDisciplina tipo){
        return (int) disciplinas.stream()
                .filter(disciplina -> disciplina.getTipo().getQuantidadeMax() == tipo.getQuantidadeMax())
                .count();
    }

    @Override
    public void cadastrar() {

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
