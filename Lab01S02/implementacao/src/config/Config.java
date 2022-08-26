package config;

public class Config {
    private static int MAX_ALUNOS_POR_DISCIPLINA;
    private static int MAX_DISCIPLINAS_OBRIGATORIAS_POR_ALUNO;
    private static int MAX_DISCIPLINAS_OPTATIVAS_POR_ALUNO;
    private static String DISCIPLINAS_PATH;
    private static String CURSOS_PATH;
    private static String USUARIOS_PATH;
    private static String ALUNOS_PATH;
    private static String PROFESSORS_PATH;

    public Config(){}

    public static int getMaxAlunosPorDisciplina() {
        return MAX_ALUNOS_POR_DISCIPLINA;
    }

    public static void setMaxAlunosPorDisciplina(int maxAlunosPorDisciplina) {
        MAX_ALUNOS_POR_DISCIPLINA = maxAlunosPorDisciplina;
    }

    public static int getMaxDisciplinasObrigatoriasPorAluno() {
        return MAX_DISCIPLINAS_OBRIGATORIAS_POR_ALUNO;
    }

    public static void setMaxDisciplinasObrigatoriasPorAluno(int maxDisciplinasObrigatoriasPorAluno) {
        MAX_DISCIPLINAS_OBRIGATORIAS_POR_ALUNO = maxDisciplinasObrigatoriasPorAluno;
    }

    public static int getMaxDisciplinasOptativasPorAluno() {
        return MAX_DISCIPLINAS_OPTATIVAS_POR_ALUNO;
    }

    public static void setMaxDisciplinasOptativasPorAluno(int maxDisciplinasOptativasPorAluno) {
        MAX_DISCIPLINAS_OPTATIVAS_POR_ALUNO = maxDisciplinasOptativasPorAluno;
    }

    public static String getDisciplinasPath() {
        return DISCIPLINAS_PATH;
    }

    public static void setDisciplinasPath(String disciplinasPath) {
        DISCIPLINAS_PATH = disciplinasPath;
    }

    public static String getCursosPath() {
        return CURSOS_PATH;
    }

    public static void setCursosPath(String cursosPath) {
        CURSOS_PATH = cursosPath;
    }

    public static String getUsuariosPath() {
        return USUARIOS_PATH;
    }

    public static void setUsuariosPath(String usuariosPath) {
        USUARIOS_PATH = usuariosPath;
    }

    public static String getAlunosPath() {
        return ALUNOS_PATH;
    }

    public static void setAlunosPath(String alunosPath) {
        ALUNOS_PATH = alunosPath;
    }

    public static String getProfessorsPath() {
        return PROFESSORS_PATH;
    }

    public static void setProfessorsPath(String professorsPath) {
        PROFESSORS_PATH = professorsPath;
    }
}
