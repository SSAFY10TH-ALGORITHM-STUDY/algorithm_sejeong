package WEEK3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ_22865_가장먼곳
 * */

public class BOJ_22865_가장먼곳 {

    static BufferedReader br;
    static StringTokenizer st;

    static int[][] map;
    static int N, M; // 자취할 땅 후보
    static final int INF = 100_000_000; // int overflow를 방지하기 위한 적정 max값

    public static void init(){
        map = new int[N + 1][N + 1]; // 플로이드워샬을 진행할 맵
        for (int rowIdx = 0; rowIdx < N+1; rowIdx++){
            Arrays.fill(map[rowIdx], INF); // 채우기 (int overflow 방지)
            for (int colIdx = 0; colIdx < N+1; colIdx++){
                if (rowIdx == colIdx){
                    map[rowIdx][colIdx] = 0;
                }
            }
        }
    }

    public static void floyd(){
        for (int mid = 1; mid < N+1; mid++){
            for (int start = 1; start < N+1; start++){
                for (int end = 1; end < N+1; end++){
                    map[start][end] = Math.min(map[start][end], map[start][mid] + map[mid][end]);
                }
            }
        }
    }

    public static void print(){
        for (int idx = 0; idx < N; idx++){
            System.out.println(Arrays.toString(map[idx]));
        }
    }

    public static void main(String[] args) throws IOException {

        // 입력
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine().trim());
        // A, B, C 가 사는 위치
        st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        init();
        // 각 간선 정보 입력
        M = Integer.parseInt(br.readLine().trim());
        for (int idx = 0; idx < M; idx++){
            st = new StringTokenizer(br.readLine().trim());
            int point1 = Integer.parseInt(st.nextToken());
            int point2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            // 길이 연결 짓기 (양방향 통행 가능)
            map[point1][point2] = cost;
            map[point2][point1] = cost;
        }

        // 플로이드 워셜 알고리즘
        floyd();
        print();
        // 친구들이 살고 있는 곳으로부터 먼 곳


    }



}
