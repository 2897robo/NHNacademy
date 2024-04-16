import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Comparator;

public class MyList<E extends Comparable<E>> implements Iterable<E> {
    // Iterable<E> : <E>라는 요소에 대해 iterate 가능하다.
    // <E extends Comparable<E>> : E는 Comparable<E>를 extends를 한다,, => E는 Comparable한 것만 받겠다.
    private List<E> list;

    public MyList() {
        this.list = new ArrayList<>();
    }

    public void sort() {
        Collections.sort(this.list);
    }

    public void sort(Comparator<E> comparator) {
        Collections.sort(this.list, comparator);
    }

    public void add(E element) { 
        this.list.add(element);
    }

    @Override
    public Iterator<E> iterator() {
        return this.list.iterator();
    }

    public List<E> getList() {
        return this.list;
    }
}

/*
 * 로그(기록)는 어떤 자료구조를 담는 것이 좋을까?
 * : 스택을 사용하는 것이 좋을 것 같은데..
 * => 어떤 자료구조를 사용할지는, 용도, 목적에 따라 정해지는 것이 좋다.
 * 자주 사용하는 기능이 무엇인지 생각해 보면서..
 * 데이터베이스에서 자료구조가 중요하다. 그래서 자료구조를 잘해야 한다.
 * 
 * 전산학 기초가 튼튼하다 : 운영체제, 자료구조, 알고리즘
 * => 그렇지만 지금 공부하기에는 늦었다. 학부 때 했어야 했다.
 * 그래서 면접 때 누가 물어 보면 대답할 수 있는 정도로만 하자.
 * 
 * 우리는 4개월 정도 공부하면 대략 강의 시간은 대학교 3년치 정도 나온다.
 * 집약적이고, 효율적이지 못하다. 두뇌에 오래 남지 못한다. 지금 과정에서, 전산학 기초를 배우는 건 장려되지 않음.
 * 
 * <T> : 단일 원소
 * <E> : 요소 (목록)
 * <K> : Key
 * <V> : Value
 */