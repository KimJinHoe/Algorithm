# Algorithm
알고리즘 및 코딩테스트 문제풀이

Boj_16235 나무재태크
- ArrayList는 삽입,삭제의 정렬에 시간이 오래 걸리니 Deque 등을 사용하면 좋다. (탐색은 속도가 빠르다)

Boj_1655_가운데를말해요
- Pque(힙정렬)는 poll()로 보면 내림차순,오름차순으로 보이지만 toArrays로 보면 트리 형태 때문에 순서대로 보이지 않는다.

Boj_11049_행렬곱셈순서
- 2차원 dp도 익숙해지자. 1차원 dp만 생각하지 말 것.

Boj_2533_사회망서비스_dp
- 다음에 한 번 더 풀어볼 것. 다른 답안을 참고해서 풀었음
- 반례를 다 찾아도 시간 문제를 해결해야 함
  - 1. 친구가 1명인 노드의 친구를 E로 설정하고 친구 관계를 끊기 (시간초과)
  - 2. 트리를 구현하고 레벨이 가장 깊은 곳부터 리프노드의 부모를 E로 설정하기. 그 후에 리프노드가 아닌 노드 중 레벨이 가장 깊은 곳부터 자신이 E가 아니면 부모를 E로 설정하기 (시간초과)

Boj_1647_도시분할계획_KruskalPque
- 2268ms 통과한 코드를 다른 사람 코드 일부분(Pque 사용)을 참고해서 개선함
- 크루스칼 알고리즘에서 Arrays.sort()에 많은 시간이 소요되는데 이를 Pque를 사용하면 개선 가능함
- 크루스칼을 쓸 때에는 Pque를 쓰자!

Boj_16946_벽부수고이동하기4_bfs
- 범위 조건을 쓰지 말고, 외각 패딩을 이용하면 조금의 메모리를 추가적으로 사용하지만 성능이 좋아짐  
    👉(nx<0 || ny<0 || ny>=N || nx>=N) 안 해도 됨

Boj_14003_가장긴증가하는부분수열5
- 나중에 다시 복습하기. 다른 사람 풀이법 참고해서 겨우 품

---
숫자 입력받기
- 아스키코드 + 비트연산자로 정수입력을 빠르게 받을 수 있음
- 코딩테스트 시험에서는 굳이 쓰지말고 백준 순위 올리고 싶으면 쓰자.
- 문자, 문자열 안됨. 문자나 문자열은 System.in.read()로 구현하자. 하지만 굳이...? (귀찮으니 BufferedReader 쓰자)
```
// 0을 포함한 자연수만 가능
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;
    }
```
```
// 정수 가능
    static int read() throws Exception {

        int c, n = System.in.read() & 15;

        boolean isNegative = n == 13;
        if (isNegative) n = System.in.read() & 15;

        while ((c = System.in.read()) > 32)
            n = (n << 3) + (n << 1) + (c & 15);

        return isNegative ? ~n + 1 : n;

    }
```
`dp를 좀 더 연습할 것` 경우의 수가 크니 %N을 하고 나머지를 출력하는 문제는 대부분 DP 문제다.
