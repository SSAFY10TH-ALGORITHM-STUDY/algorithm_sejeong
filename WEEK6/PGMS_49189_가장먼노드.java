package WEEK6;

import java.util.*;

class PGMS_49189_가장먼노드 {

    // 1. 입력 받는 graph -> 지정 X
    public static ArrayList<ArrayList<Integer>> graph;
    public static int[][] edge;
    public static boolean[] visited;
    public static int n;
    // 2. 초기화 배열
    public void init(){

        graph = new ArrayList<>();

        for (int idx =0 ; idx <= n; idx++){
            graph.add(new ArrayList<>()); // 초기화
        }

        // 배열 초기화
        for (int [] vertexList : edge){
            int nodeA = vertexList[0];
            int nodeB = vertexList[1];
            // 양방향 그래프 생성
            graph.get(nodeA).add(nodeB);
            graph.get(nodeB).add(nodeA);
        }

        // 방문 초기화
        visited = new boolean[n+1];
    }

    // 2. BFS 진행
    public static int bfs(){

        int result = 0;
        int maxDepth = 0;

        Deque<int[]> queue = new ArrayDeque<>();

        queue.add(new int[]{1, 0});
        visited[1] = true;


        while(!queue.isEmpty()){
            int [] list = queue.poll();
            int node = list[0]; // 첫번째 노드
            int depth = list[1];

            // 1.만약 최대 길이 노드라면
            if (maxDepth == depth) {
                result ++;
            }
            // 더 긴 거리에 노드가 있다면
            else if(maxDepth < depth){
                maxDepth = depth;
                result = 1;
            }

            // 그래프 탐색
            for (int idx =0 ; idx < graph.get(node).size(); idx++){
                int target = graph.get(node).get(idx);
                if (!visited[target]) {
                    queue.add(new int[] {target, depth + 1});
                    visited[target] = true;
                }

            }


        }


        return result;
    }

    public int solution(int n, int[][] edge) {
        int answer = 0;
        this.edge = edge;
        this.n = n;

        init(); // 초기화

        // bfs진행
        answer = bfs();


        return answer;
    }
}