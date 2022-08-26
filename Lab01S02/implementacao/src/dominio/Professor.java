package dominio;

public class Professor extends Usuario {
    private Disciplina[] disciplinas;

    public Professor(String nome){
        super(nome);
    }

    @Override
    public void cadastrar() {

    }

    public String verAlunosMatriculados(Disciplina disciplina){
        StringBuilder sb = new StringBuilder();
        sb.append(disciplina.getNome() + "\t"+ disciplina.getTipo().name() + "\n\n");
        disciplina.getAlunosMatriculados().stream().forEach(aluno -> sb.append("- " +aluno.getNome() + "\n"));
        return sb.toString();
    }
}
