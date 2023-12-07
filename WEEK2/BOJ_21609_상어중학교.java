package WEEK2;


import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * BOJ_21609_상어중학교
 * - 검은색 블록 -1 / 무지개 블록 0 / 일반 블록 M가지 색상
 * - 블록 그룹 : 연결된 블록의 집합
 * - 블록 그룹 조건
 *   1. 일반 블록 적어도 1개 이상이여야 한다. & 일반 블록의 색은 모두 동일
 *   2. 검은 블록 포함 X
 *   3. 무지개 블록은 무제한
 *   4. 블록의 개수는 2보다 크거나 같아야 한다.
 *
 * - 문제 조건
 *   1. 가장 큰 블록 그룹 (크기가 동일하면, 무지개 블록이 많은 블록 그룹)
 *   2. 가장 큰 블록 그룹 제거 -> B제곱 포인트 획득
 *   3. 중력 작용
 *   4. 90도 반시계 회전
 *   5. 격자 중력 작용
 *   -> 중력 작용 시, 검정색 블록을 제외한 모든 블록 행번호가 큰칸으로 이동
 *
 * */
public class BOJ_21609_상어중학교 {

    static BufferedReader br;
    static StringTokenizer st;

    // 입력
    static int mapSize;
    static int colorCnt;
    static int [][] map;
    static boolean [][] visited;

    // 조건
    static final int BLACK = -1;
    static final int RAINBOW = 0;
    static final int NONE = -2; // -1, 0이 조건에서 사용되었으므로, 중복되지 않는 값은 공백의 값!!

    // delta 함수
    static int[] deltaRow = {-1, 1, 0, 0};
    static int[] deltaCol = {0, 0, -1, 1};

    // 최대 그룹 찾기
    static ArrayList<Point> maxGroup;
    static int MAX_RAINBOW_CNT;

    // 결과
    static boolean flag; // 유효한 값
    static int resultPoint = 0;

    // 맵 유효성 검사
    public static boolean inMap(int rowIdx, int colIdx){
        return (rowIdx >= 0 && rowIdx < mapSize && colIdx >= 0 && colIdx < mapSize);
    }

    // maxGroup을 구하는 메서드
    public static void compareMaxGroup(ArrayList<Point> group, int rainbowSize){

        // maxGroup 보다 group의 크기가 더 크면
        if (group.size() > maxGroup.size()){
            maxGroup = group; // 갱신
            MAX_RAINBOW_CNT = rainbowSize;
        }
        // 만약 그룹의 크기가 동일하다면? -> 무지개 블록의 수가 가장 많은 블록 그룹
        else if (group.size() == maxGroup.size()){
            // == 해준 이유는 같으면 -> row , col 클수록 우선순위이므로 갱신
            if (rainbowSize >= MAX_RAINBOW_CNT){
                maxGroup = group;
                MAX_RAINBOW_CNT = rainbowSize;
            }
        }

    }

    // rainbow 방문 여부 초기화
    public static void switchRainbow(ArrayList<Point> rainbow){
        // rainbow는 다른 그룹에서도 포함할 수 있으므로, 방문 처리를 해제한다.
        for (Point point : rainbow){
            visited[point.x][point.y] = false;
        }
    }

    // 최대 값 찾기
    public static void bfs(int rowIdx, int colIdx){

        Point inputPoint = new Point(rowIdx, colIdx);
        int COLOR = map[rowIdx][colIdx]; // 초기값의 색상

        Deque<Point> queue = new ArrayDeque<>();
        queue.add(inputPoint);
        visited[rowIdx][colIdx] = true;

        ArrayList<Point> group = new ArrayList<>();
        group.add(inputPoint);
        ArrayList<Point> rainbow = new ArrayList<>();

        while (!queue.isEmpty()) {
            Point current = queue.pop();

            for (int direction = 0; direction < 4; direction++){
                int nextRow = current.x + deltaRow[direction];
                int nextCol = current.y + deltaCol[direction];

                if (!inMap(nextRow, nextCol) || visited[nextRow][nextCol]) continue;  // 맵 밖에 있거나, 방문했으면 PASS
                // 현재 COLOR와 RAINBOW만 가능
                if (map[nextRow][nextCol] == COLOR || map[nextRow][nextCol] == RAINBOW){
                    Point nextPoint = new Point(nextRow, nextCol); // 다음 이동 좌표 객체

                    if (map[nextRow][nextCol] == RAINBOW) {
                        rainbow.add(nextPoint);
                    }

                    queue.add(nextPoint); // 큐에 추가
                    visited[nextRow][nextCol] = true;
                    group.add(nextPoint);
                }


            }
        } // end for bfs

        // group의 크기가 2보다 작으면, 그룹이 아니다.
        if (group.size() < 2){
            return;
        }

        compareMaxGroup(group, rainbow.size()); // 최대 그룹 찾기
        switchRainbow(rainbow); // rainbow 초기화
    }

    // 가장 큰 그룹 찾기
    public static void findMaxGroup(){

        visited = new boolean[mapSize][mapSize]; // visited 초기화

        // max값 찾기
        maxGroup = new ArrayList<>(); // 가장 큰 배열을 찾아줄 것
        MAX_RAINBOW_CNT = Integer.MIN_VALUE;

        for (int rowIdx = 0; rowIdx < mapSize; rowIdx ++){
            for (int colIdx = 0; colIdx < mapSize; colIdx ++ ){
                // 일반 블록일 경우 (자연수) 방문하지 않은 것
                if (map[rowIdx][colIdx] > 0 && !visited[rowIdx][colIdx]){
                    bfs(rowIdx, colIdx); // 그룹 탐색
                }
            }
        }

    }

    // 가장 큰 그룹 터지기
    public static void removeMaxGroup(){
        for (Point point : maxGroup){
            map[point.x][point.y] = NONE;
        }
    }

    // 점수 먹기
    public static void getPoint(int MAX_GROUP_SIZE){
        resultPoint += MAX_GROUP_SIZE * MAX_GROUP_SIZE;
    }

    // 중력 적용
    public static void gravity(){
        int empty;
        // 열기준으로 row를 밑에서부터 탐색
        for (int colIdx = 0; colIdx < mapSize; colIdx++){
            empty = 0; // 공백 체크
            for (int rowIdx = mapSize - 1 ; rowIdx >= 0; rowIdx--){
                if (map[rowIdx][colIdx] == NONE) { // 공백일 경우
                    empty += 1;
                }
                else if (map[rowIdx][colIdx] == BLACK){ // 블랙일 경우 적용 안됨
                    empty = 0;
                }
                else { // 무지개색과 일반색은 index 이동
                    // 만약 현재 row와 공백의 합이 현재 row와 다르다면?
                    if (rowIdx + empty != rowIdx){
                        map[rowIdx + empty][colIdx] = map[rowIdx][colIdx]; // 변경된 값에 이동값 넣기
                        map[rowIdx][colIdx] = NONE; // 내렸으므로 초기화해주기
                    }
                }
            }
        }
    }

    // copy
    public static int[][] copyMap(int[][] map){
        int[][] newMap = new int[mapSize][mapSize];
        for (int rowIdx =0 ; rowIdx<mapSize; rowIdx++){
            for (int colIdx = 0 ; colIdx < mapSize; colIdx++){
                newMap[rowIdx][colIdx] = map[rowIdx][colIdx];
            }
        }
        return newMap;
    }

    // 90도 반시계방향
    public static void rotate90(){
        int[][] newMap = copyMap(map); // map 복사 /

        // 방향 회전
        for (int rowIdx = 0 ; rowIdx < mapSize; rowIdx++){
            for (int colIdx = 0; colIdx < mapSize; colIdx++){
                newMap[rowIdx][colIdx] = map[colIdx][mapSize - rowIdx - 1];
            }
        }

        // 맵 갱신
        map = newMap;
    }

    public static void autoPlay(){
        while(true){
            // 1. 크기가 가장 큰 블록 그룹을 찾는다.
            findMaxGroup();
            if (maxGroup.size() == 0) break; // maxGroup이 존재하지않으면, 종료

            // 2. 1에서 찾은 블록 그룹의 모든 블록을 제거한다. & 점수를 획득한다.
            getPoint(maxGroup.size()); // 점수 획득
            removeMaxGroup(); // 제거

            // 3. 격자에 중력이 작용한다.
            gravity();

            // 4. 격자가 90도 반시계 방향으로 회전한다.
            rotate90();

            // 5. 다시 중력이 작용한다.
            gravity();
        }

    }

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer((br.readLine().trim()));

        mapSize = Integer.parseInt(st.nextToken());
        colorCnt = Integer.parseInt(st.nextToken());
        map = new int[mapSize][mapSize];

        for (int rowIdx = 0; rowIdx < mapSize; rowIdx++){
            st = new StringTokenizer(br.readLine().trim());
            for (int colIdx = 0; colIdx < mapSize; colIdx++){
                map[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
            }
        }
        autoPlay(); // 반복
        System.out.println(resultPoint);
    }

    // 디버깅용 print
    public static void print(){
        System.out.println("결과 확인");
        for (int rowIdx = 0; rowIdx < mapSize; rowIdx ++){
            System.out.println(Arrays.toString(map[rowIdx]));
        }
        System.out.println();
    }


}
