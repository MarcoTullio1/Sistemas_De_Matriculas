package util.menu;

public class Option<T,V> {
    T key;
    V value;

    public Option(T key,V value){
        this.key = key;
        this.value = value;
    }

    public T getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}
