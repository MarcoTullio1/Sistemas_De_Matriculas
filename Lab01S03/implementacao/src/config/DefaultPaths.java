package config;

public enum DefaultPaths {

    DISCIPLINAS("./src/bin/disciplinas.txt"),
    CURSOS("./src/bin/cursos.txt"),
    USUARIOS("./src/bin/usuarios.txt"),
    ALUNOS("./src/bin/alunos.txt"),
    PROFESSORS("./src/bin/professores.txt"),
    Config("./src/config.txt");

    String path;

    DefaultPaths(String path){
        this.path = path;
    }

    public String getPath(){
        return this.path;
    }
}
