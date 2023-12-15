package WEEK3;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * BOJ_20058_마법사상어와 파이어스톰
 *
 * - 문제 조건이 조금 부실한듯? 문제만 보고 이해가 잘 안되는데 ,,
 *
 * - 입력 : 2^N * 2^N - 얼음판
 *
 * - 1. 파이어 스톰
 *  2^L * 2^L 크기의 격자로 나눈다.
 *  모든 부분 격자를 시계 방향으로 90도 회전한다.
 *
 * - 2. 녹기
 *  얼음이 있는 칸 3개 또는 그 이상과 인접해있지 않은 칸은 얼음의 양이 1개 줄ㅇ든다.
 *  그렇다면 녹는 조건 ?
 *  얼음에 충분히 둘러쌓이지 못한 얼음은 녹아서 1개 줄어있는다는 의미
 *
 * - 3. 구해야 하는 대상
 *  - 남아있는 얼음의 양
 *  - 얼음 덩어리 중 가장 큰 덩어리
 *
 * */


public class BOJ_20058_마법사상어와파이어스톰 {

    static BufferedReader br;
    static StringTokenizer st;

    static int mapSize;
    static int[][] map;
    static boolean[][] visited;

    static int iceCount = 0; // 남앙있는 얼음의 양
    static int maxIceArea = 0; // 얼음 덩어리 중 가장 큰 덩어리

    static int[] deltaRow = {-1, 1, 0, 0};
    static int[] deltaCol = {0, 0, -1, 1};
    static final int EMPTY = 0;

    public static int[][] copyMap(int [][] map){
        int[][] newMap = new int[mapSize][mapSize];
        for (int rowIdx =0 ; rowIdx < mapSize; rowIdx++){
            newMap[rowIdx] = map[rowIdx].clone();
        }
        return newMap;
    }

    public static void print(int [][] map){
        for (int idx = 0; idx < mapSize; idx++){
            System.out.println(Arrays.toString(map[idx]));
        }
    }

    // 2^L * 2^L 크기의 부분 격자로 나눈 후 시계 방향으로 90도 회전
    public static void fireStom(int L){
        int miniMapSize = (int) Math.pow(2, L);
        int[][] newMap = copyMap(map); // 복사 맵

        for (int rowIdx = 0; rowIdx < mapSize; rowIdx += miniMapSize) {
            for (int colIdx = 0; colIdx < mapSize; colIdx += miniMapSize){
                // 격자 단위 회전
                for (int miniRowIdx = 0; miniRowIdx < miniMapSize; miniRowIdx++){
                    for (int miniColIdx = 0; miniColIdx < miniMapSize; miniColIdx++){
                        newMap[rowIdx + miniColIdx][colIdx + miniMapSize - miniRowIdx - 1] = map[rowIdx + miniRowIdx][colIdx + miniColIdx];
                    }
                }
            }
        }
        map = newMap; // map 치환
    }

    public static boolean inMap(int rowIdx, int colIdx){
        return (rowIdx >= 0 && rowIdx < mapSize && colIdx >= 0 && colIdx < mapSize);
    }

    public static void meltIce(){

        HashSet<Point> meltList = new HashSet<>(); // 녹는 대상

        for (int rowIdx = 0; rowIdx < mapSize; rowIdx++){
            for (int colIdx = 0; colIdx < mapSize; colIdx++){

                // 녹는 대상 파악
                int meltCount = 0; // 녹을 수 있는 대상
                for (int direction = 0; direction < 4; direction ++){
                    int nextRow = rowIdx + deltaRow[direction];
                    int nextCol = colIdx + deltaCol[direction];

                    if (!inMap(nextRow, nextCol)) continue;

                    // 얼음이 존재하는 칸의 개수
                    if (map[nextRow][nextCol] != EMPTY){
                        meltCount += 1;
                    }
                } // end for direction

                // 얼음에 충분히 둘러쌓여 있지 못한 얼음들은 녹아서 1씩 감소
                if (meltCount < 3 && map[rowIdx][colIdx] != EMPTY){
                    meltList.add(new Point(rowIdx, colIdx)); // 녹이는 대상 넣기
                }
            }
        } // end for map

        // 전체에서 녹인다.
        for (Point meltPoint : meltList){
            map[meltPoint.x][meltPoint.y] -= 1; // 1씩 녹는다.
        }
    }

    public static void findMaxIceArea(Point point){

        Deque<Point> queue = new ArrayDeque<>();
        queue.add(point);
        int iceArea = 0;

        while(!queue.isEmpty()){
            Point currentPoint = queue.poll();

            for (int direction = 0; direction < 4; direction ++){
                int nextRow = currentPoint.x + deltaRow[direction];
                int nextCol = currentPoint.y + deltaCol[direction];

                if (!inMap(nextRow, nextCol)) continue;
                if (visited[nextRow][nextCol] || map[nextRow][nextCol] <= EMPTY) continue;

                queue.add(new Point(nextRow, nextCol));
                visited[nextRow][nextCol] = true;
                iceArea += 1;
            }
        }

        // 최대값 비교
        maxIceArea = Math.max(iceArea, maxIceArea); // 최대 영역 구하기
    }

    // 남아있는 얼음 내용 찾기
    public static void findResult(){

        Deque<Point> queue;
        visited = new boolean[mapSize][mapSize]; // 방문 여부 확인

        for (int rowIdx = 0; rowIdx < mapSize; rowIdx++){
            for (int colIdx = 0; colIdx < mapSize; colIdx++){

                iceCount += map[rowIdx][colIdx]; //  전체 탐색 후 ice 수량 누적

                // findMaxIceArea
                if (!visited[rowIdx][colIdx] && map[rowIdx][colIdx] > EMPTY){
                    findMaxIceArea(new Point(rowIdx, colIdx));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));

        // 2^n개의 줄에는 각
        st = new StringTokenizer(br.readLine().trim());

        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        mapSize = (int) Math.pow(2, N); // 2^N 이 mapSize
        map = new int[mapSize][mapSize];

        for (int rowIdx = 0; rowIdx < mapSize; rowIdx++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int colIdx = 0; colIdx < mapSize; colIdx++) {
                map[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
            }
        } // end for input map

        st = new StringTokenizer(br.readLine().trim());
        for (int idx = 0; idx < Q; idx++){
            fireStom(Integer.parseInt(st.nextToken())); // 회전
            meltIce();
        }
        
        // 정답 찾기
        findResult();
        System.out.println(iceCount);
        System.out.println(maxIceArea);
    }

}