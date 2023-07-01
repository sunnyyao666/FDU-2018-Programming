package Task3;

public class Task3 {
    public static void main(String[] args) {
        QueueOfInteger queue = new QueueOfInteger(11);
        for (int i = 0; i <= 10; i++) queue.push(i);
        for (int i = 0; i <= 10; i++) System.out.println("Element:" + queue.pop() + " Size:" + queue.getSize());
    }
}