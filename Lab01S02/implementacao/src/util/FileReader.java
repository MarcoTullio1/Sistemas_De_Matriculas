package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FileReader<T> {
    public FileReader(){};

    public List<T> carregarListaObjetosDeArquivoTexto(String path){
        T object = null;
        List<T> objects = new ArrayList<>();
        try{
            ObjectInputStream file = new ObjectInputStream(new FileInputStream(path));
            do{
                object = (T) file.readObject();
                objects.add(object);
            }while(object != null);
            file.close();
            return objects;
        }catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro na leitura do arquivo!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<T>();
    }
    public Set<T> carregarSetObjetosDeArquivoTexto(String path){
        T object = null;
        Set<T> objects = new HashSet<>();
        try{
            ObjectInputStream file = new ObjectInputStream(new FileInputStream(path));
            do{
                object = (T) file.readObject();
                objects.add(object);
            }while(object != null);
            file.close();
            return objects;
        }catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro na leitura do arquivo!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new HashSet<>();
    }
}
