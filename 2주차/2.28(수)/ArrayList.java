interface list {
    void add(int value);
    void add(int index, int value);
    int remove(int index);
    int get(int index);
}

public class ArrayList implements list {
    int[] arr = new int[50];
    int size = 0;

    @Override
    public void add(int value) {
        add(size, value);
    }

    @Override
    public void add(int index, int value) {
        if(index > size) {
            throw new IllegalArgumentException();
        }
        else if(index <= size) {
            size++;
            for(int i=size; i>index; i--) {
                arr[i] = arr[i-1];
            }
            arr[index] = value;
        }
    }

    @Override
    public int remove(int index) {
        int tmp = get(index);
        for(int i=index; i<=size; i++) {
            arr[i] = arr[i+1];
        }
        size--;
        return tmp;
    }

    @Override
    public int get(int index) {
        return arr[index];
    }

    public void print() {
        for(int i=0; i<size; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void main(String[] args) {
        ArrayList arr_test = new ArrayList();
        arr_test.add(1);
        arr_test.add(2);
        arr_test.add(3);
        arr_test.add(4);
        arr_test.add(5);
        System.out.println("delete : " + arr_test.remove(2));
        System.out.println("get : " + arr_test.get(3));
        arr_test.add(3, 8);
        arr_test.print();
    }
}