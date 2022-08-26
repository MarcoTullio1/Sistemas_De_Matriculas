import config.Config;
import config.DefaultPaths;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        try{
            init();
        }catch(FileNotFoundException e){
            System.err.println("Problema ao buscar o caminho dos arquivos!\n" + e.getMessage());
        }catch(Exception e){
            System.err.println("Houve um erro ao iniciar o aplicativo!\n"+ e.getMessage());
        }
    }
    static void init() throws Exception{
        Scanner input = new Scanner(new File(DefaultPaths.Config.getPath()));
        int nConfigs = Integer.parseInt(input.nextLine());
        int[] maxConfigs = new int[nConfigs];
        for(int i = 0; i < nConfigs ; i++){
            String line = input.nextLine().replace(" ", "");
            line = line.substring(line.lastIndexOf('=')+1, line.length());
            int n = Integer.parseInt(line);
            maxConfigs[i] = n;
        }
        Config.setAlunosPath(DefaultPaths.ALUNOS.getPath());    
        Config.setCursosPath(DefaultPaths.CURSOS.getPath());
        Config.setDisciplinasPath(DefaultPaths.DISCIPLINAS.getPath());
        Config.setProfessorsPath(DefaultPaths.PROFESSORS.getPath());
        Config.setUsuariosPath(DefaultPaths.USUARIOS.getPath());
        Config.setMaxAlunosPorDisciplina(maxConfigs[0]);
        Config.setMaxDisciplinasObrigatoriasPorAluno(maxConfigs[1]);
        Config.setMaxDisciplinasOptativasPorAluno(maxConfigs[2]);
    }
}
