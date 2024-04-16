import java.util.LinkedList;
import java.util.List;

public class Box {
    public <T> List<T> arrayToList(T[] array) {
        List<T> list = new LinkedList<T>();
        
        for(T t:array) {
            list.add(t);
        }

        return list;
    }

    public static void main(String[] args) {
        Integer[] tmp = {1,2,3,4};
        
        Box me = new Box();
        List<Integer> list = me.<Integer>arrayToList(tmp);
    }
}