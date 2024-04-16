import java.util.*;

public class BoyFriends implements Iterable<BoyFriend> {
    private List<BoyFriend> list = new ArrayList<>();

    public Iterator<BoyFriend> iterator() {
        return this.list.iterator();
    }

    public void add(BoyFriend boy) {
        this.list.add(boy);
    }

    public void remove(int index) {
        this.list.remove(index);
    }
    
    public static void main(String[] args) {
        BoyFriend b = new BoyFriend("James", 22);
    }


}
