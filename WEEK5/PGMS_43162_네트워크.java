package WEEK5;
import java.util.*;

public class PGMS_43162_네트워크 {
    static boolean[] visited;
    static Deque<Integer> queue;
    static int[][] computers;
    static int n;

    public void bfs(int computerIdx){
        queue = new ArrayDeque<>();

        visited[computerIdx] = true;
        queue.add(computerIdx);

        while(!queue.isEmpty()){
            int current = queue.poll();
            visited[current] = true;

            for (int connect = 0; connect < n; connect++){
                if (connect != current && computers[current][connect] == 1){
                    if (!visited[connect])
                        queue.add(connect);
                }
            }
        }
        return;
    }

    public int solution(int n, int[][] computers) {
        int answer = 0;
        this.n = n;
        this.computers = computers;
        visited = new boolean[n];

        for(int idx = 0; idx < n ; idx++){
            if (!visited[idx]){
                bfs(idx);
                answer += 1;
            }
        }

        return answer;
    }
}
