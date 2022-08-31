import java.util.ArrayList;
import java.util.List;

public class Aluno extends Usuario implements Observable {
    private List<Disciplina> disciplinasOptativas;
    private List<Disciplina> disciplinasObrigatorias;

    public Aluno(String nome, Observer observer){
        super(nome);
        this.disciplinasObrigatorias = new ArrayList<>();
        this.disciplinasOptativas = new ArrayList<>();
    }

    public boolean matricular(Disciplina disciplina){
        return true;
    }

    public boolean cancelarMatricula(Disciplina disciplina){
        return true;
    }

    public List<Disciplina> getDisciplinasOptativas(){
        return this.disciplinasOptativas;
    }

    public List<Disciplina> getDisciplinasObrigatorias(){
        return this.disciplinasObrigatorias;
    }

    public boolean pagarMatricula(Disciplina disciplina){
        return true;
    }

    public String relatorioDeDividas(){
        return "";
    }

    @Override
    public void attach(Observer observer) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void detach(Observer observer) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void notifyObservers() {
        // TODO Auto-generated method stub
        
    }
}
