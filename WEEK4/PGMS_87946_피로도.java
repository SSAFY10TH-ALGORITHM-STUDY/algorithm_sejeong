package WEEK4;

import java.util.*;
class PGMS_87946_피로도 {
    static boolean[] visited;
    static int answer = 0;

    public static void permutatation(int tired, int cnt){
        for (int idx = 0; idx < dungeons.length; idx++){
            if (!visited[idx] && dungeons[idx][0] <= k){
                visited[idx] = true;
                permutation(tired - dungeons[idx][1], cnt + 1)
                visited[idx] = false;
            }
        }
        answer = Math.max(answer, cnt);
    }

    public int solution(int k, int[][] dungeons) {
        this.k = k;
        visited = new boolean[dungeons.length];

        permutations(k, 0);



        return answer;
    }
}