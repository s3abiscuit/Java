package container;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class PriorityQueueTest {

    public static void main(String[] args) {
        // throw exception: add remove element
        // return null: offer poll peek
        Queue<Integer> queue1 = new PriorityQueue<>();
        queue1.add(2);
        queue1.add(1);
        queue1.add(3);
        System.out.println(queue1.element());

        while (!queue1.isEmpty()) {
            Integer i = queue1.remove();
            System.out.println(i);
        }

        Comparator<Student> comparator = new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return (o1.id - o2.id);
            }
        };

        Queue<Student> queue2 = new PriorityQueue<>(comparator);
        queue2.offer(new Student(1, "B"));
        queue2.offer(new Student(1, "A"));
        queue2.offer(new Student(3, "C"));
        System.out.println(queue2.peek());

        while (!queue2.isEmpty()) {
            Student s = queue2.poll();
            System.out.println(s.toString());
        }
    }

    public static class Student {
        private final int id;
        private final String name;

        public Student(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public String toString() {
            return id + "-" + name;
        }
    }
}
/* output
1
1
2
3
1-A
1-A
1-B
 */