public class Aluno extends Usuario implements Observable {
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
