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


//Senha da secretaria: root

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
    static Menu secretariaLogadaMenu;

    static Menu visualizarObjetosMenu;

    public static void main(String[] args) {
        init();
        mainMenuHandler();
    }

    static void init() {
        try {
            Scanner input = new Scanner(new File(DefaultPaths.Config.getPath()));
            int nConfigs = Integer.parseInt(input.nextLine());
            int[] maxConfigs = new int[nConfigs];
            for (int i = 0; i < nConfigs; i++) {
                String line = input.nextLine().replace(" ", "");
                line = line.substring(line.lastIndexOf('=') + 1);
                int n = Integer.parseInt(line);
                maxConfigs[i] = n;
            }
            input.close();
            Config.setMaxAlunosPorDisciplina(maxConfigs[0]);
            Config.setMaxDisciplinasObrigatoriasPorAluno(maxConfigs[1]);
            Config.setMaxDisciplinasOptativasPorAluno(maxConfigs[2]);
        } catch (FileNotFoundException e) {
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

    static void printarTodosOsCursos() {
        for (Curso c : cursos) {
            System.out.println(c.getNome());
            for (Disciplina d : c.getDisciplinas()) {
                System.err.println("- " + d.getNome() + "\n");
            }
            System.out.println("-------------------------\n");
        }
    }

    static void printarTodasAsDisciplinas() {
        for (Disciplina d : disciplinas) {
            System.out.println(d.getNome() + " - " + d.getTipo().name());
        }
    }

    static void printarTodosOsAlunos() {
        for (Aluno a : alunos) {
            System.out.println(a.getNome());
        }
    }

    static void printarTodosOsProfessores() {
        for (Professor p : professors) {
            System.out.println(p.getNome());
        }
    }

    static Disciplina buscarDisciplina(String nome) {
        Optional<Disciplina> possivelDisciplinaEncontrada = disciplinas.stream().filter(d -> d.getNome().toUpperCase().equals(nome.toUpperCase()))
                .findFirst();
        return possivelDisciplinaEncontrada.orElseThrow();
    }

    static Curso buscarCurso(String nome) {
        Optional<Curso> possivelCursoEncontrada = cursos.stream().filter(d -> d.getNome().toUpperCase().equals(nome.toUpperCase()))
                .findFirst();
        return possivelCursoEncontrada.orElseThrow();
    }

    static void carregarObjetos() {
        FileReader<Aluno> FR_ALUNO = new FileReader<>();
        FileReader<Professor> FR_PROFESSOR = new FileReader<>();
        FileReader<Disciplina> FR_DISCIPLINA = new FileReader<>();
        FileReader<Curso> FR_CURSO = new FileReader<>();

        alunos = FR_ALUNO.carregarSetObjetosDeArquivoTexto(Config.getAlunosPath());
        professors = FR_PROFESSOR.carregarSetObjetosDeArquivoTexto(Config.getProfessorsPath());
        disciplinas = FR_DISCIPLINA.carregarSetObjetosDeArquivoTexto(Config.getDisciplinasPath());
        cursos = FR_CURSO.carregarSetObjetosDeArquivoTexto(Config.getCursosPath());
    }

    static void criarAluno(String nome, String senha) {
        Aluno novo = new Aluno(nome, senha, financeiro);
        alunos.add(novo);
        salvarObjetos(alunos, Config.getAlunosPath());
    }

    static void criarProfessor(String nome, String senha) {
        Professor novo = new Professor(nome, senha);
        professors.add(novo);
        salvarObjetos(professors, Config.getProfessorsPath());
    }

    static void criarDisciplina(String nome, TipoDisciplina tipo) {
        Disciplina nova = new Disciplina(nome, tipo);
        disciplinas.add(nova);
        salvarObjetos(disciplinas, Config.getDisciplinasPath());
    }

    static void criarCurso(String nome) {
        Curso novo = new Curso(nome);
        cursos.add(novo);
        salvarObjetos(cursos, Config.getCursosPath());
    }

    static <T> void salvarObjetos(Set<T> objects, String path) {
        // Re-salva todo o set, mudar dps.
        FileWriter<T> _FW = new FileWriter<T>();
        _FW.salvarBinario(objects, path);
    }

    static void menuBuilder() {
        Map<Integer, String> mainMenuOptions = new HashMap<>();
        Map<Integer, String> alunoMenuOptions = new HashMap<>();
        Map<Integer, String> alunoLogadoMenuOptions = new HashMap<>();
        Map<Integer, String> professorMenuOptions = new HashMap<>();
        Map<Integer, String> professorLogadoMenuOptions = new HashMap<>();
        Map<Integer, String> secretariaLogadaMenuOptions = new HashMap<>();
        Map<Integer, String> visualizarObjetosOptions = new HashMap<>();

        mainMenuOptions.put(1, "Area do aluno");
        mainMenuOptions.put(2, "Area do professor");
        mainMenuOptions.put(3, "Secretaria");
        mainMenuOptions.put(4, "Sair");

        alunoMenuOptions.put(1, "Logar");
        alunoMenuOptions.put(2, "Cadastrar");
        alunoMenuOptions.put(3,"Sair");

        alunoLogadoMenuOptions.put(1, "Visualizar disciplinas matriculadas");
        alunoLogadoMenuOptions.put(2, "Matricular");
        alunoLogadoMenuOptions.put(3, "Cancelar Matricula");
        alunoLogadoMenuOptions.put(4, "Sair");

        professorMenuOptions.put(1, "Logar");
        professorMenuOptions.put(2, "Cadastrar");
        professorMenuOptions.put(3, "Voltar");

        professorLogadoMenuOptions.put(1, "Visualizar alunos cadastrados");
        professorLogadoMenuOptions.put(2, "Se cadastrar em uma disciplina");
        professorLogadoMenuOptions.put(3, "Sair");

        secretariaLogadaMenuOptions.put(1, "Cadastrar curso");
        secretariaLogadaMenuOptions.put(2, "Cadastrar disciplina");
        secretariaLogadaMenuOptions.put(3, "Atualizar ementa do curso");
        secretariaLogadaMenuOptions.put(4, "Visualizar");
        secretariaLogadaMenuOptions.put(5, "Sair");

        visualizarObjetosOptions.put(1, "Visualizar alunos");
        visualizarObjetosOptions.put(2, "Visualizar cursos");
        visualizarObjetosOptions.put(3, "Visualizar professores");
        visualizarObjetosOptions.put(4, "Visualizar disciplinas");
        visualizarObjetosOptions.put(5, "Sair");

        mainMenu = new Menu("Main Menu", "Sistema de gestão acadêmica", mainMenuOptions);
        alunoMenu = new Menu("Aluno", "Area do aluno", alunoMenuOptions);
        alunoLogadoMenu = new Menu("Aluno", "Bem vindo", alunoLogadoMenuOptions);
        professorMenu = new Menu("Professor", "Area do professor", professorMenuOptions);
        professorLogadoMenu = new Menu("Professor", "Bem vindo", professorLogadoMenuOptions);
        secretariaLogadaMenu = new Menu("Secretaria", "Bem vindo", secretariaLogadaMenuOptions);
        visualizarObjetosMenu = new Menu("Visualizar", "", visualizarObjetosOptions);
    }

    static void mainMenuHandler() {
        Scanner input = new Scanner(System.in);
        List<Integer> menuValidOptionsList = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        mainMenu.mainMenu();
        switch (Menu.optionHandler(input.nextLine(), menuValidOptionsList)) {
            case 1: {
                alunoMenuHandler(input);
                break;
            }
            case 2: {
                professorMenuHandler(input);
                break;
            }
            case 3: {
                secretariaLogadaMenu.subMenu();
                System.out.println("Senha (default: root):");
                if (input.nextLine().equals("root")) {
                    secretariaLogadaMenuHandler(input);
                } else {
                    System.err.println("Senha inválida");
                    Menu.pausaTeclado(input);
                    mainMenuHandler();
                }
                break;
            } case 4:{
                System.exit(0);
            }
        }
        mainMenuHandler();
    }

    // Menu do aluno section
    static void alunoMenuHandler(Scanner input) {
        List<Integer> alunoMenuOptionsList = new ArrayList<>(Arrays.asList(1, 2, 3));
        alunoMenu.mainMenu();

        switch (Menu.optionHandler(input.nextLine(), alunoMenuOptionsList)) {
            case 1: {
                System.out.println("Digite o nome do aluno");
                String nome = input.nextLine();
                System.out.println("Digite a senha do aluno");
                String senha = input.nextLine();
                Optional<Aluno> aluno = alunos.stream().
                        filter(a -> a.equals(new Aluno(nome, senha)))
                        .findFirst();
                aluno.ifPresentOrElse(a -> alunoLogadoMenuHandler(a, input), () -> {
                    System.err.println("Aluno não encontrado!");
                    return;
                });
                break;
            }
            case 2: {
                System.out.println("Digite o nome do novo aluno");
                String nome = input.nextLine();
                System.out.println("Digite a senha do novo aluno");
                String senha = input.nextLine();
                criarAluno(nome, senha);
                break;
            }
            case 3:
                return;
        }
        alunoMenuHandler(input);
    }

    static void alunoLogadoMenuHandler(Aluno aluno, Scanner input) {
        List<Integer> alunoLogadoMenuOptionsList = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        financeiro.setAluno(aluno);
        alunoLogadoMenu.mainMenu();

        switch (Menu.optionHandler(input.nextLine(), alunoLogadoMenuOptionsList)) {
            case 1: {
                for (Disciplina d : aluno.getDisciplinas()) {
                    System.out.println(d.getNome() + " - " + d.getTipo().name());
                }
                Menu.pausaTeclado(input);
                break;
            }
            case 2: {
                Menu.clearScreen();
                System.out.println("Disciplinas disponíveis:\n");
                printarTodasAsDisciplinas();
                System.out.println("\nDigite o nome da disciplina desejada:");
                Disciplina disc = buscarDisciplina(input.nextLine().toUpperCase());
                try {
                    disc.matricularAluno(aluno);
                    System.out.println("Matriculado com sucesso!");
                    salvarObjetos(alunos, Config.getAlunosPath());
                    salvarObjetos(disciplinas, Config.getDisciplinasPath());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                Menu.pausaTeclado(input);

                break;
            }
            case 3: {
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
            case 4:
                return;
        }

        alunoLogadoMenuHandler(aluno, input);
    }
    //end Menu do aluno section

    // Menu do professor section


    static void professorMenuHandler(Scanner input) {
        List<Integer> professorMenuOptionList = new ArrayList<>(Arrays.asList(1, 2, 3));
        professorMenu.mainMenu();

        switch (Menu.optionHandler(input.nextLine(), professorMenuOptionList)) {
            case 1: {
                System.out.println("Digite o nome do professor");
                String nome = input.nextLine();
                System.out.println("Digite a senha do professor");
                String senha = input.nextLine();
                Optional<Professor> professor = professors.stream().
                        filter(p -> p.equals(new Professor(nome, senha)))
                        .findFirst();
                professor.ifPresentOrElse(p -> professorLogadoMenuHandler(input, p), () -> {
                    System.err.println("Aluno não encontrado!");
                    return;
                });
                break;
            }
            case 2: {
                System.out.println("Digite o nome do novo professor");
                String nome = input.nextLine();
                System.out.println("Digite a senha do novo professor");
                String senha = input.nextLine();
                criarProfessor(nome, senha);
                break;
            }
            case 3:
                return;
        }
        professorMenuHandler(input);
    }

    static void professorLogadoMenuHandler(Scanner input, Professor professor) {
        List<Integer> menuValidOptionsList = new ArrayList<>(Arrays.asList(1, 2, 3));
        professorLogadoMenu.mainMenu();

        switch (Menu.optionHandler(input.nextLine(), menuValidOptionsList)) {
            case 1: {
                Menu.clearScreen();
                System.out.println("Disciplinas nas quais o professor faz parte:\n");
                professor.getDisciplinas().forEach(d -> System.out.println(d.getNome() + "\n"));
                System.out.println("Diga o nome da disciplina para ver os alunos matriculados\n");
                System.out.println(professor.verAlunosMatriculados(buscarDisciplina(input.nextLine())));
                Menu.pausaTeclado(input);
                break;
            }
            case 2:{
                Menu.clearScreen();
                System.out.println("Disciplinas disponíveis:\n");
                printarTodasAsDisciplinas();
                System.out.print("Digite o nome da disciplina: ");
                try{
                    professor.getDisciplinas().add(buscarDisciplina(input.nextLine()));
                }catch(Exception e){
                    System.err.println("Erro ao cadastrar disciplina\n");
                    Menu.pausaTeclado(input);
                    professorLogadoMenuHandler(input, professor);
                    break;
                }
                salvarObjetos(professors, Config.getProfessorsPath());
                System.out.println("Sucesso!");
                Menu.pausaTeclado(input);
                break;
            }
            case 3:
                return;
        }
        professorLogadoMenuHandler(input,professor);
    }
    // end Menu do professor section

    // Menu da secretaria section

    static void secretariaLogadaMenuHandler(Scanner input) {
        List<Integer> menuValidOptionsList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        secretariaLogadaMenu.mainMenu();

        switch (Menu.optionHandler(input.nextLine(), menuValidOptionsList)) {
            case 1: {
                //cadastrar curso;
                Menu.clearScreen();
                System.out.println("Nome do curso: ");
                criarCurso(input.nextLine());
                break;
            }
            case 2: {
                //cadastrar disciplina;
                Menu.clearScreen();
                System.out.println("Nome da disciplina: ");
                String nomeDisciplina = input.nextLine();
                System.out.println("Tipo de disciplina:\nOBRIGATORIA ou OPTATIVA");
                String tipoDisciplina = input.nextLine().toUpperCase();
                if (tipoDisciplina.equalsIgnoreCase(TipoDisciplina.OBRIGATORIA.name()) || tipoDisciplina.equalsIgnoreCase(TipoDisciplina.OPTATIVA.name()))
                    criarDisciplina(nomeDisciplina, TipoDisciplina.valueOf(tipoDisciplina.toUpperCase()));
                else {
                    System.err.println("Tipo inválido!");
                    Menu.pausaTeclado(input);
                }
                secretariaLogadaMenuHandler(input);
                break;
            }
            case 3: {
                Menu.clearScreen();
                printarTodosOsCursos();
                System.out.println("\nInsira o nome do curso no qual a ementa será cadastrada!" +
                        "\nPara sair deste menu, digite um nome de curso inválido!");
                try {
                    Curso c = buscarCurso(input.nextLine());
                    Menu.clearScreen();
                    printarTodasAsDisciplinas();
                    System.out.println("\nEscolha as disciplinas que irão fazer parte da ementa deste curso!" +
                            "\nPara sair do menu, digite 'sair'");
                    String str = input.nextLine();
                    ;
                    do {
                        c.addDisciplina(buscarDisciplina(str));
                        str = input.nextLine();
                    } while (!str.equalsIgnoreCase("sair"));
                    salvarObjetos(cursos, Config.getCursosPath());
                    System.out.println("Cadastrado com sucesso!");

                } catch (NullPointerException e) {
                    System.err.println("não encontrado!");
                    Menu.pausaTeclado(input);
                    secretariaLogadaMenuHandler(input);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                } finally {
                    Menu.pausaTeclado(input);
                }
                break;
            }
            case 4: {
                visualizarObjetosHandler(input);
                break;
            }
            case 5: {
                //sair;
                return;
            }
        }
        secretariaLogadaMenuHandler(input);
    }

    /*
1 - Visualizar cursos
2 - Visualizar alunos
3 - Visualizar professores
4 - Visualizar disciplinas
     */

    static void visualizarObjetosHandler(Scanner input) {
        List<Integer> menuValidOptionsList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));

        visualizarObjetosMenu.mainMenu();

        switch (Menu.optionHandler(input.nextLine(), menuValidOptionsList)) {
            case 1: {
                printarTodosOsAlunos();
                break;
            }
            case 2: {
                printarTodosOsCursos();
                break;
            }
            case 3: {
                printarTodosOsProfessores();
                break;
            }
            case 4: {
                printarTodasAsDisciplinas();
                break;
            }
            case 5:
                return;
        }
        Menu.pausaTeclado(input);
        visualizarObjetosHandler(input);
    }
}

    // end Menu da secretaria section