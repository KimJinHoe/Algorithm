package Algorithm.ex;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class ex1_스택큐 {
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<>();
		Stack<Character> stack = new Stack<>();
		
		map.put("java01", "1111");
		map.put("java02", "2222");
		map.put("java03", "3333");
		map.put("java04", "4444");
		System.out.println(map);
		System.out.println("java01 : " + map.get("java01"));
		map.remove("java03");
		System.out.println(map);
		
		stack.push('1');
		stack.push('2');
		stack.push('3');
		System.out.println(stack);
		System.out.println("peek : " + stack.peek());
		stack.pop();
		System.out.println(stack);
		System.out.println("---------------------------------------------------");
		Queue<String> que = new LinkedList<>();
		//Queue<String> que = new ArrayDeque<>();
		System.out.println(que.size() + "//" + que.isEmpty());
		que.offer("김진회");
		que.offer("박철진");
		que.offer("오지용");
		que.offer("박기조");
		System.out.println(que.size() + "//" + que.isEmpty());
		System.out.println(que.poll());
		System.out.println(que.size() + "//" + que.isEmpty());
		System.out.println(que.peek());
		System.out.println(que.size() + "//" + que.isEmpty());
		System.out.println(que.poll());
		System.out.println(que.poll());
		System.out.println(que.poll());
		System.out.println(que.size() + "//" + que.isEmpty());
		System.out.println(que.poll()); //null을 반환함
		
	}
}
