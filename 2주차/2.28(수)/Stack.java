public class Stack<E> {
    private E[] array;
    private int index = 0;

    @SuppressWarnings("unchecked")
    public Stack(int size) {
        this.array = (E[])new Object[size];
    }

    public void push(E i) {
        this.array[this.index++] = i;
    }

    public E pop() {
        return this.array[--this.index];
    }
}

class Test {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<Integer> (10);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        
        int i = (Integer)stack.pop();
        System.out.println(i);
    }
}