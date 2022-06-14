package Algorithm.ex;

import java.util.Comparator;
import java.util.PriorityQueue;
//자동 정렬해주는 큐
public class ex1_우선순위큐_힙정렬_Comparable {
	public static void main(String[] args) {
		PriorityQueue<Integer> pque = new PriorityQueue<Integer>();
		pque.offer(10);
		pque.offer(30);
		pque.offer(5);
		pque.offer(50);
		pque.offer(20);

		while (pque.size() != 0) {
			System.out.println(pque.poll());
		}
		System.out.println("----------------------------------------");
		//원래는 no로 Comparable하지만 score으로 바꿈
		PriorityQueue<Student> stuque = new PriorityQueue<Student>(new Comparator<Student>() {
			@Override
			public int compare(Student o1, Student o2) {
				return o1.score - o2.score;
			}
		});
		stuque.offer(new Student(5, 10));
		stuque.offer(new Student(7, 60));
		stuque.offer(new Student(3, 40));
		stuque.offer(new Student(1, 30));
		
		while (stuque.size() != 0) {
			System.out.println(stuque.poll().score);
		}
	}
}

class Student implements Comparable<Student> {
	
	int no, score;

	public Student(int no, int score) {
		this.no = no;
		this.score = score;
	}

	@Override
	public int compareTo(Student o) {
		return this.no - o.no;
	}
}
