public class Stack {
    int [] element;
    int index = 0;

    public Stack(int size) {
        this.element = new int[size];
    }

    public void push(int element) {
        this.element[index++] = element;
    }

    public int pop() {
        return this.element[--index];
    }
}

class StackTest {
    public static void main(String[] args) {
        Stack stack = new Stack(10);

        stack.push(10);
        System.out.println(stack.pop());
    }
}