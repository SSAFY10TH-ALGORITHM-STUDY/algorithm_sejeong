package WEEK5;

import java.util.*;
import java.awt.*;

/**
 * 게임맵 최단 거리 -> java
 * 전에 파이썬으로 풀었던 문제다.
 * BFS 접근으로만 문제를 풀어서 DFS로 문제를 풀었다.
 * 정확성은 다 맞는데, 효율성에서 시간초과가 난다.
 * 이 문제 제출 할 때는 BFS로 다시 풀어야겠다,,
 *
 * */

public class PGMS_1844_게임맵최단거리 {

    static int[][] map;
    static int rowSize;
    static int colSize;
    static boolean[][] visited;
    static int totalDepth = Integer.MAX_VALUE;

    static int[] deltaRow = {-1, 0, 1, 0};
    static int[] deltaCol = {0, -1, 0, 1};

    static final int WALL = 0; //

    public static boolean validMap(int rowIdx, int colIdx) {
        return rowIdx >= 0 && rowIdx < rowSize && colIdx >= 0 && colIdx < colSize;
    }

    public static void dfs(int rowIdx, int colIdx, int depth){

        // 종료 조건
        if (rowIdx == 0 && colIdx == 0){

            // min 값 갱신
            totalDepth = Math.min(totalDepth, depth);
            // System.out.println(depth);

            return;
        }

        visited[rowIdx][colIdx] = true;
        for (int direction = 0 ; direction < 4; direction ++ ){
            int nextRow = rowIdx + deltaRow[direction];
            int nextCol = colIdx + deltaCol[direction];

            // 종료조건
            if (!validMap(nextRow, nextCol)) continue; // 맵밖에 나가거나
            if (map[nextRow][nextCol] == WALL) continue; // 벽이거나
            if (visited[nextRow][nextCol]) continue;

            // 동일에서 방문
            visited[nextRow][nextCol] = true;
            dfs(nextRow, nextCol, depth + 1);
            visited[nextRow][nextCol] = false;

        }


    }

    public int solution(int[][] maps) {
        this.map = maps; // 저장
        this.rowSize = maps.length;
        this.colSize = maps[0].length;
        this.visited = new boolean[rowSize][colSize];

        dfs(rowSize -1, colSize - 1, 1); // dfs -> 맨 뒤부터 출발 (현재칸까지 depth 포함)

        // 결과
        if (totalDepth == Integer.MAX_VALUE){
            return -1;
        }else{
            return totalDepth;
        }
    }

}
