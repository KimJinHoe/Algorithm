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

`dp를 좀 더 연습할 것`
