package WEEK1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/**
 * BOJ_13975_파일 합치기 3
 *
 * 소설의 모든 장을 쓰고 나서는 각 장이 쓰여진 파일을 합쳐 최종적으로 소설의 완성본이 들어있는 한 개의 파일
 * 1. 두 개의 파일을 합쳐서 하나의 임시파일을 만들고,
 * 2. 이 임시파일이나 원래의 파일을 계속 두 개씩 합쳐서 파일을 합쳐나가고,
 * 3. 최종적으로는 하나의 파일로 합침
 * 결론 : 최종적인 한 개의 파일을 완성하는 데 필요한 비용의 총 합
 *
 * 메모
 * - 문제에서 60+100+150 = 310 이 부분에서 헷갈렸는데 (30+30) -> 60 / 40 + 50 -> 90 / 60 + 90 -> 150 묶음으로 60 + 90 + 150 = 300 만들 수 있다.
 * - 즉, 우선순위 큐로, 최소 뭉치끼리 더해주고 다시 큐에 넣는 방법으로 해결 가능
 * - k는 1,000,000 (백만)까지 / 10,000 파일의 크기 만 까지 -> int 범위 초과 가능 -> long
 * */
public class BOJ_13975_파일합치기3 {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    public static void testCase() throws IOException{
        PriorityQueue<Long> pq = new PriorityQueue<>(); // 우선순위 큐

        int k = Integer.parseInt(br.readLine().trim()); // 소설을 구하는 장수
        st = new StringTokenizer(br.readLine().trim()); // 소설

        for (int idx = 0; idx < k; idx ++) {
            pq.add(Long.parseLong(st.nextToken()));     //  Long으로 입력
        }
        long answer = 0; // 최종 결과

        // 우선 순위 큐에서 최소값 두개를 합한 값을 누적해주고, 다시 큐에 넣는다 !
        while(pq.size() > 1) {
            long num1 = pq.poll();
            long num2 = pq.poll();
            answer += num1 + num2;
            pq.add(num1 + num2);
        }
        sb.append(answer).append("\n");
    }

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= testCase; tc++){
            testCase();
        }
        System.out.println(sb); // 결과 출력
    }
}
