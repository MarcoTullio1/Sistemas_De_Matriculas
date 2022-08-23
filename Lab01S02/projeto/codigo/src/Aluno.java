import java.util.Observable;
import java.util.Observer;

public class Aluno extends Usuario implements Observer {
    private Disciplina[] pagamentosPendentes;

    public boolean matricular(Disciplina disciplina){
    }

    public boolean cancelarMatricula(Disciplina disciplina){
    }

    public Disciplina[] getStateDividas(){
    }

    public boolean pagarMatricula(Disciplina disciplina){
    }

    public String relatorioDeDividas(){
    }

    @Override
    public void update(Observable o, Object arg) {
        // TODO Auto-generated method stub
        
    }
}
