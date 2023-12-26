package WEEK4;

import java.util.*;
class PGMS_87946_피로도 {
    static boolean[] visited;
    static int answer = 0;
    static int[][] dungeons;

    public static void dfs(int currentTired, int count){
        for (int idx = 0; idx < dungeons.length; idx++){
            // 방문하지 않은 것 -> 현재 남은 피로도가 남아있다면?
            if (!visited[idx] && dungeons[idx][0] <= currentTired){
                visited[idx] = true;
                dfs(currentTired - dungeons[idx][1] , count+1);
                visited[idx] = false;
            }

        }
        answer = Math.max(answer, count);
    }

    public int solution(int k, int[][] dungeons) {
        this.dungeons = dungeons;

        visited = new boolean[dungeons.length];
        dfs(k, 0);

        return answer;
    }
}