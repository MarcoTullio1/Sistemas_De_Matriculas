import config.Config;
import config.DefaultPaths;
import dominio.*;
import dominio.enums.TipoDisciplina;
import util.FileReader;
import util.FileWriter;
import util.menu.Menu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class App {

    static Set<Disciplina> disciplinas;
    static Set<Aluno> alunos;
    static Set<Professor> professors;
    static Set<Curso> cursos;
    static Financeiro financeiro = new Financeiro();
    static Menu mainMenu;
    static Menu alunoMenu;
    static Menu alunoLogadoMenu;
    static Menu professorMenu;
    static Menu professorLogadoMenu;
    static Menu secretariaMenu;
    static Menu secretariaLogadaMenu;

    public static void main(String[] args) {
        init();
        mainMenuHandler();
    }

    static void init(){
        try{
            Scanner input = new Scanner(new File(DefaultPaths.Config.getPath()));
            int nConfigs = Integer.parseInt(input.nextLine());
            int[] maxConfigs = new int[nConfigs];
            for(int i = 0; i < nConfigs ; i++){
                String line = input.nextLine().replace(" ", "");
                line = line.substring(line.lastIndexOf('=')+1);
                int n = Integer.parseInt(line);
                maxConfigs[i] = n;
            }
            input.close();
            Config.setMaxAlunosPorDisciplina(maxConfigs[0]);
            Config.setMaxDisciplinasObrigatoriasPorAluno(maxConfigs[1]);
            Config.setMaxDisciplinasOptativasPorAluno(maxConfigs[2]);
        }catch(FileNotFoundException e){
            System.err.println("Houve um erro ao carregar o arquivo:\n" + e.getMessage());
            System.exit(31231);
        }
        Config.setAlunosPath(DefaultPaths.ALUNOS.getPath());
        Config.setCursosPath(DefaultPaths.CURSOS.getPath());
        Config.setDisciplinasPath(DefaultPaths.DISCIPLINAS.getPath());
        Config.setProfessorsPath(DefaultPaths.PROFESSORS.getPath());
        Config.setUsuariosPath(DefaultPaths.USUARIOS.getPath());

        carregarObjetos();
        menuBuilder();
    }

    static void printarTodosOsCursos(){
        for(Curso c : cursos){
            System.out.println(c.getNome());
        }
    }

    static void printarTodasAsDisciplinas(){
        for(Disciplina d : disciplinas){
            System.out.println(d.getNome() + " - " + d.getTipo().name());
        }
    }
    static void printarTodosOsAlunos(){
        for(Aluno a : alunos){
            System.out.println(a.getNome());
        }
    }
    static void printarTodosOsProfessores(){
        for(Professor p : professors){
            System.out.println(p.getNome());
        }
    }

    static Disciplina buscarDisciplina(String nome){
        Optional<Disciplina> possivelDisciplinaEncontrada = disciplinas.stream().filter(d -> d.getNome().toUpperCase().equals(nome))
                .findFirst();
        return possivelDisciplinaEncontrada.orElseThrow();
    }

    static void carregarObjetos(){
        FileReader<Aluno> FR_ALUNO = new FileReader<>();
        FileReader<Professor> FR_PROFESSOR = new FileReader<>();
        FileReader<Disciplina> FR_DISCIPLINA = new FileReader<>();
        FileReader<Curso> FR_CURSO = new FileReader<>();

        alunos = FR_ALUNO.carregarSetObjetosDeArquivoTexto(Config.getAlunosPath());
        professors = FR_PROFESSOR.carregarSetObjetosDeArquivoTexto(Config.getProfessorsPath());
        disciplinas = FR_DISCIPLINA.carregarSetObjetosDeArquivoTexto(Config.getDisciplinasPath());
        cursos = FR_CURSO.carregarSetObjetosDeArquivoTexto(Config.getCursosPath());
    }

    static void criarAluno(String nome, String senha){
        Aluno novo = new Aluno(nome, senha, financeiro);
        alunos.add(novo);
        salvarObjetos(alunos, Config.getAlunosPath());
    }

    static void criarProfessor(String nome, String senha){
        Professor novo = new Professor(nome, senha);
        professors.add(novo);
        salvarObjetos(alunos, Config.getProfessorsPath());
    }

    static void criarDisciplina(String nome, TipoDisciplina tipo){
        Disciplina nova = new Disciplina(nome, tipo);
        disciplinas.add(nova);
        salvarObjetos(alunos, Config.getDisciplinasPath());
    }

    static void criarCurso(String nome){
        Curso novo = new Curso(nome);
        cursos.add(novo);
        salvarObjetos(alunos, Config.getCursosPath());
    }

    static<T> void salvarObjetos(Set<T> objects, String path){
        // Re-salva todo o set, mudar dps.
        FileWriter<T> _FW = new FileWriter<T>();
        _FW.salvarBinario(objects, path);
    }

    static void menuBuilder(){
        Map<Integer, String> mainMenuOptions = new HashMap<>();
        Map<Integer, String> alunoMenuOptions = new HashMap<>();
        Map<Integer, String> alunoLogadoMenuOptions = new HashMap<>();
        Map<Integer, String> professorMenuOptions = new HashMap<>();
        Map<Integer, String> professorLogadoMenuOptions = new HashMap<>();
        Map<Integer, String> secretariaMenuOptions = new HashMap<>();
        Map<Integer, String> secretariaLogadaMenuOptions = new HashMap<>();
        Map<Integer, String> visualizarObjetosOptions = new HashMap<>();

        mainMenuOptions.put(1, "Area do aluno");
        mainMenuOptions.put(2, "Area do professor");
        mainMenuOptions.put(3, "Secretaria");
        mainMenuOptions.put(4, "Sair");

        alunoMenuOptions.put(1, "Logar");
        alunoMenuOptions.put(2, "Cadastrar");

        alunoLogadoMenuOptions.put(1, "Visualizar disciplinas matriculadas");
        alunoLogadoMenuOptions.put(2, "Matricular");
        alunoLogadoMenuOptions.put(3, "Cancelar Matricula");
        alunoLogadoMenuOptions.put(4, "Sair");

        professorMenuOptions.put(1, "Logar");
        professorMenuOptions.put(2,"Cadastrar");
        professorMenuOptions.put(3, "Voltar");

        professorLogadoMenuOptions.put(1, "Visualizar alunos cadastrados");
        professorLogadoMenuOptions.put(2, "Sair");

        secretariaMenuOptions.put(1, "Logar");
        secretariaMenuOptions.put(2, "Sair");

        secretariaLogadaMenuOptions.put(1,"Cadastrar curso");
        secretariaLogadaMenuOptions.put(2, "Cadastrar disciplina");
        secretariaLogadaMenuOptions.put(3, "Atualizar ementa do curso");
        secretariaLogadaMenuOptions.put(4, "Visualizar");
        secretariaLogadaMenuOptions.put(5, "Sair");

        mainMenu = new Menu("Main Menu", "Sistema de gestão acadêmica", mainMenuOptions);
        alunoMenu = new Menu("Aluno", "Area do aluno", alunoMenuOptions);
        alunoLogadoMenu = new Menu("Aluno", "Bem vindo", alunoLogadoMenuOptions);
        professorMenu = new Menu("Professor", "Area do professor", professorMenuOptions);
        professorLogadoMenu = new Menu("Professor", "Bem vindo", professorLogadoMenuOptions);
        secretariaMenu = new Menu("Secretaria", "Area da secretaria", secretariaMenuOptions);
        secretariaLogadaMenu = new Menu("Secretaria", "Bem vindo", secretariaLogadaMenuOptions);
    }

    static void mainMenuHandler(){
        Scanner input = new Scanner(System.in);
        List<Integer> menuValidOptionsList = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        mainMenu.mainMenu();
        switch(Menu.optionHandler(input.nextLine(), menuValidOptionsList)){
            case 1:{
                alunoMenuHandler(input);
                break;
            }
            case 2:{
                System.out.println(alunos);
                break;
            }
            case 3:{

                break;
            }
            default: mainMenuHandler();
        }
    }

    // Menu do aluno section
    static void alunoMenuHandler(Scanner input){
        List<Integer> alunoMenuOptionsList = new ArrayList<>(Arrays.asList(1,2,3));
        alunoMenu.mainMenu();

        switch (Menu.optionHandler(input.nextLine(), alunoMenuOptionsList)){
            case 1:{
                System.out.println("Digite o nome do aluno");
                String nome = input.nextLine();
                System.out.println("Digite a senha do aluno");
                String senha = input.nextLine();
                Optional<Aluno> aluno = alunos.stream().
                        filter(a -> a.equals(new Aluno(nome, senha)))
                        .findFirst();
                aluno.ifPresentOrElse(a -> alunoLogadoMenuHandler(a, input), () ->{System.err.println("Aluno não encontrado!"); return;});
                break;
            }
            case 2:{
                System.out.println("Digite o nome do novo aluno");
                String nome = input.nextLine();
                System.out.println("Digite a senha do novo aluno");
                String senha = input.nextLine();
                criarAluno(nome, senha);
                break;
            }
            case 3: return;
            default: alunoMenuHandler(input);
        }
        alunoMenuHandler(input);
    }

    static void alunoLogadoMenuHandler(Aluno aluno, Scanner input){
        List<Integer> alunoLogadoMenuOptionsList = new ArrayList<>(Arrays.asList(1,2,3,4));
        financeiro.setAluno(aluno);
        alunoLogadoMenu.mainMenu();

        switch (Menu.optionHandler(input.nextLine(), alunoLogadoMenuOptionsList)){
            case 1:{
                for(Disciplina d : aluno.getDisciplinas()){
                    System.out.println(d.getNome() + " - " + d.getTipo().name());
                }
                Menu.pausaTeclado(input);
                break;
            }
            case 2:{
                Menu.clearScreen();
                System.out.println("Disciplinas disponíveis:\n");
                printarTodasAsDisciplinas();
                System.out.println("\nDigite o nome da disciplina desejada:");
                Disciplina disc = buscarDisciplina(input.nextLine().toUpperCase());
                try {
                    disc.matricularAluno(aluno);
                    System.out.println("Matriculado com sucesso!");
                    salvarObjetos(alunos, Config.getAlunosPath());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                Menu.pausaTeclado(input);

                break;
            }
            case 3:{
                Menu.clearScreen();
                System.out.println("Digite o nome da disciplina na qual você deseja cancelar a matricula:\n");
                Disciplina disc = buscarDisciplina(input.nextLine().toUpperCase());
                try {
                    disc.removerMatricula(aluno);
                    salvarObjetos(alunos, Config.getAlunosPath());
                    System.out.println("Matricula cancelada com sucesso!");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                Menu.pausaTeclado(input);
                alunoLogadoMenuHandler(aluno, input);
            }
            case 4: return;
            default: alunoLogadoMenuHandler(aluno, input);
        }

        alunoLogadoMenuHandler(aluno, input);
    }
    //end Menu do aluno section


    // Menu do professor section

    // end Menu do professor section

    // Menu da secretaria section

    // end Menu da secretaria section


}