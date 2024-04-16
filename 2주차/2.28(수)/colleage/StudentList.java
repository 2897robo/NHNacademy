package colleage;
import java.util.List;
import java.util.ArrayList;

public class StudentList<T> implements Iterable<T> {
    List<T> list = new ArrayList<T>();

    public void add(T item) {
        list.add(item);
    }

    public java.util.Iterator<T> iterator() {
        return list.iterator();
    }
}
