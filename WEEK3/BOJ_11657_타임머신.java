package WEEK3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ_11657_타임머신
 * - 벨만포드 알고리즘 : 한 노드에서 다른 노드까지의 최단거리를 구하는 알고리즘
 * - 다익스트라와 벨만포드의 차이 ? : 다익스트라는 음수간선이 존재하면 최단거리를 찾을 수 없는 상황 발생
 *                             반면 벨만포드는 매번 모든 간선을 전부 확인하므로, 최단거리 찾을 수 있다.
 * - 모든 간선의 비용이 양수일 때는 다익스트라를 / 음수 간선이 포함되어 있으면 벨만포드 사용
 *
 * 벨만 포드 알고리즘 수행과정
 * 1. 출발 노드 설정
 * 2. 최단 거리 테이블 초기화
 * 3. 정점 마다 모두 반복
 *  3-1. 모든 간선 E개를 한개씩 확인
 *  3-2. 각 간선을 거쳐 다른 노드로 가는 비용을 계산하여, 최단 거리 테이블 갱신 *
 * d
 *
 * */
public class BOJ_11657_타임머신 {

    static BufferedReader br;
    static StringTokenizer st;

    static int cityCnt;
    static int busCnt;

    static Bus[] busList;
    static int [] distance; // 최단 거리 배열
    static final int INF = 500_000_000;

    static class Bus{
        int startCity;
        int endCity;
        int costTime;

        public Bus(int startCity, int endCity, int costTime){
            this.startCity = startCity;
            this.endCity = endCity;
            this.costTime = costTime;
        }

    }

    public static void init(){
        distance = new int[cityCnt + 1];
        for (int idx = 0; idx < cityCnt + 1; idx++){
            distance[idx] = INF;
        }
    }

    // 모든 정점마다 반복한다.
    public static boolean bellmanFord(int start){
        distance[start] = 0; // 벨만 포드의 시작점은 0으로 시작

        // 각 city를 모두 순회한다.
        for (int idx = 1; idx < cityCnt + 1; idx++){
            // 반복마다 모든 간선 확인
            for (int busIdx = 0; busIdx < busCnt; busIdx++){

                int currentBus = busList[busIdx].startCity;
                int nextBus = busList[busIdx].endCity;
                int costTime = busList[busIdx].costTime;

                if (distance[currentBus] == INF)
                    continue;

                // 현재 간선을 거쳐서 다른 노드로 이동하는 거리가 짧을 경우
                if (distance[nextBus] > (distance[currentBus] + costTime)){
                    distance[nextBus] = distance[currentBus] + costTime;

                    // n번째 라운드에서 값이 갱신되면, 음수 순환 존재
                    if (idx == cityCnt){
                        return false;
                    }
                }

            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine().trim());
        cityCnt = Integer.parseInt(st.nextToken());
        busCnt = Integer.parseInt(st.nextToken());

        init();
        // 버스 노선 정보 입력
        busList = new Bus[busCnt]; // 버스 노선 리스트
        for (int idx = 0; idx < busCnt; idx++){
            st = new StringTokenizer(br.readLine().trim());
            int startCity = Integer.parseInt(st.nextToken());
            int endCity = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            busList[idx] = new Bus(startCity, endCity, cost);
        }
        if (bellmanFord(1)){
            for (int idx = 2; idx < cityCnt + 1; idx++) {
                if (distance[idx] == INF) // 도달 불가
                    System.out.println("-1");
                else {
                    System.out.println(distance[idx]);
                }
            }
        }
        else {
            System.out.println(-1); // 음수 순환 존재
        }
    }

}
