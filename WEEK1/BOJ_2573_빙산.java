package WEEK1;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * BOJ_2573_빙산
 *
 * - 바다에 해당되는 칸 -> 0
 * - 빙산의 높이는 바닷물에 많이 접해있는 부분에서 더 빨리 준다. (동서남북 네 방향으로 붙어 있는 0의 개수만큼 줄어든다.)
 * 구해야할 것 : 한덩어리 빙산이 주어질 때, 이 빙산이 두 덩어리 이상으로 분리되는 "최초의 시간(년)"
 * 만약 다 녹아도 두 덩어리 이상 분리되지 않으면 0 출력
 *
 * 풀이
 * 전체 : 빙산 while
 * 1. bfs로 빙산의 개수 찾기
 * 2. 빙산 상하좌우로 녹이기
 * */

public class BOJ_2573_빙산 {

    static BufferedReader br;
    static StringTokenizer st;

    // 맵 정보 입력
    static int rowSize;
    static int colSize;
    static int[][] map;
    static int[][] nextMap;

    static final int SURFACE = 0; // 빙하 수면
    static boolean[][] visited;

    // delta
    static int[] deltaRow = {-1, 1, 0, 0};
    static int[] deltaCol = {0, 0, -1, 1};

    // 맵 밖으로 나가는 지 확인
    public static boolean inMap(int rowIdx, int colIdx ){
        if (rowIdx < 0 || rowIdx >= rowSize || colIdx < 0 || colIdx >= colSize){
            return false;
        }
        return true;
    }


    public static void bfs(int rowIdx, int colIdx){
        // BFS
        Deque<Point> queue = new ArrayDeque<>();
        queue.add(new Point(rowIdx, colIdx));
        visited[rowIdx][colIdx] = true;
        while(!queue.isEmpty()){
            Point current = queue.poll();

            for (int direction = 0; direction < 4; direction++){
                int nextRow = current.x + deltaRow[direction];
                int nextCol = current.y + deltaCol[direction];

                // 조건
                if (!inMap(nextRow, nextCol)) continue; // 맵 밖으로 나갔거나
                if (visited[nextRow][nextCol] || map[nextRow][nextCol] == SURFACE) continue; // 물이거나 방문했으면 무시

                queue.add(new Point(nextRow, nextCol));
                visited[nextRow][nextCol] = true;
            }
        }
    }

    // 빙산의 위치 찾는다.
    public static int findIceberg() {
        int icebergCnt = 0;
        // 방문용
        visited = new boolean[rowSize][colSize];
        for (int rowIdx = 0; rowIdx < rowSize; rowIdx ++){
            for (int colIdx = 0; colIdx < colSize; colIdx ++){
                if (map[rowIdx][colIdx] > SURFACE && !visited[rowIdx][colIdx]) {
                    bfs(rowIdx, colIdx);
                    icebergCnt++;
                }
            }
        }
        return icebergCnt;
    }

    // 원본 카피
    public static int[][] copyArray(int [][] array){
        // 깊은 복사
        int[][] newArray = new int[rowSize][colSize];
        for (int rowIdx = 0; rowIdx < rowSize; rowIdx ++){
            newArray[rowIdx] = array[rowIdx].clone(); // 배열 하나에서는 클론
        }
        return newArray;
    }

    // 빙하가 녹는다.
    public static void meltIceberg() {
        nextMap = copyArray(map); // 빙하가 녹은 후의 맵

        for (int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
            for (int colIdx = 0; colIdx < colSize; colIdx++) {
                // 빙하가 존재하지 않는 다면, 넘기기
                if (map[rowIdx][colIdx] == SURFACE) continue;
                // 만약 빙하가 존재한다면 상하 좌우 SURFACE 개수 세기
                int countSurface = 0;
                for (int direction = 0; direction < 4; direction++) {
                    int nextRow = rowIdx + deltaRow[direction];
                    int nextCol = colIdx + deltaCol[direction];
                    if (map[nextRow][nextCol] == SURFACE) countSurface++;
                }
                // 다음 배열에 빼주기
                nextMap[rowIdx][colIdx] -= countSurface;
                // 근데 빙산의 높이보다 사방의 물이 차있는게 더 높았다면, 0으로 만들어 준다.
                if (nextMap[rowIdx][colIdx] < SURFACE) nextMap[rowIdx][colIdx] = SURFACE;
            }
        }
    }


    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine().trim());
        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());

        // 맵 입력
        map = new int[rowSize][colSize];
        for (int rowIdx = 0; rowIdx < rowSize; rowIdx ++ ){
            st = new StringTokenizer(br.readLine().trim());
            for (int colIdx = 0; colIdx < colSize; colIdx++) {
                map[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
            }
        } // end for input map


        // Run
        int time = 0;
        while(true){
            int icebergCount = findIceberg(); // 빙하의 개수 찾기
            // 빙하가 두 덩어리 이상 분리되거나, 전부 다 녹으면
            if (icebergCount >= 2 || icebergCount == 0){
                if (icebergCount == 0) time = 0; // 다 녹을 때까지 분리 안되면 0 출력
                break;
            }
            meltIceberg(); // 빙하 녹이기
            map = nextMap; // 맵 갱신
            time++;
        }
        System.out.println(time); // 결과
    }

}
