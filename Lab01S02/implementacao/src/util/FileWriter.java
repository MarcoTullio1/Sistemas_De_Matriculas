package util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FileWriter<T>{

    public FileWriter(){}

    /**
     * Salva uma lista de T objetos contidos em um Map
     *
     * @param objects
     * @param path
     */
    public void salvarBinario(Map<String, T> objects, String path) {
        ObjectOutputStream saida = null;
        try {
            saida = new ObjectOutputStream(new FileOutputStream(path));
            for (T obj : objects.values()) {
                saida.writeObject(obj);
            }
            saida.close();
            System.out.println("Salvo");
        } catch (FileNotFoundException fe) {
            System.out.println("Arquivo não encontrado, ou permissão negada. Tente novamente com outro arquivo");
            System.exit(1);
        } catch (IOException ex) {
            System.out.println("Problemas na operação de E/S.");
            System.out.println(ex.getMessage());
            System.exit(1);
        }
    }

    /**
     * Salva uma lista de T objetos contidos em uma lista
     *
     * @param objects
     * @param path
     */
    public void salvarBinario(List<T> objects, String path) {
        try {
            ObjectOutputStream saida = new ObjectOutputStream(new FileOutputStream(path));
            for (T obj : objects) {
                saida.writeObject(obj);
            }
            saida.close();
        } catch (FileNotFoundException fe) {
            System.out.println("Arquivo não encontrado, ou permissão negada. Tente novamente com outro arquivo");
            System.exit(1);
        } catch (IOException ex) {
            System.out.println("Problemas na operação de E/S.");
            System.out.println(ex.getMessage());
            System.exit(1);
        }
    }
    /**
     * Salva uma lista de T objetos contidos em uma lista
     *
     * @param objects
     * @param path
     */
    public void salvarBinario(Set<T> objects, String path) {
        ObjectOutputStream saida = null;
        try {
            saida = new ObjectOutputStream(new FileOutputStream(path));
            for (T obj : objects) {
                saida.writeObject(obj);
            }
            saida.close();
        } catch (FileNotFoundException fe) {
            System.out.println("Arquivo não encontrado, ou permissão negada. Tente novamente com outro arquivo");
            System.exit(1);
        } catch (IOException ex) {
            System.out.println("Problemas na operação de E/S.");
            System.out.println(ex.getMessage());
            System.exit(1);
        }
    }
}
