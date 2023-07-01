package Task3;

public class QueueOfInteger {
    private int[] elements;
    private int size;

    public QueueOfInteger() {
        this(16);
    }

    public QueueOfInteger(int capacity) {
        elements = new int[capacity];
    }

    public int push(int value) {
        if (size == elements.length) {
            int[] temp = new int[elements.length * 2];
            System.arraycopy(elements, 0, temp, 0, elements.length);
            elements = temp;
        }
        size++;
        return elements[size-1] = value;
    }

    public int pop() {
        if (!this.empty()) {
            int a = elements[0];
            for (int i = 0; i < size - 1; i++) elements[i] = elements[i + 1];
            size--;
            return a;
        } else
            return 0;
    }

    public int peek() {
        return elements[0];
    }

    public boolean empty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }
}