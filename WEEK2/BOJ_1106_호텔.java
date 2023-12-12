package WEEK2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ_1106_호텔
 * - 어떤 도시에서 9원을 들여 홍보하면 3명의 고객이 늘어난다.
 * - 호텔의 고객을 적어도 C명 늘이기 위해, 형택이가 투자해야 하는 돈의 최솟값을 구하는 프로그램
 * 참고 : https://jyeonnyang2.tistory.com/318
 * 조건
 * 1. 정수배로 돈을 투자할 수 있다는 조건
 * 2. C명이 아니라, "최소 C명"의 고객을 늘리기 위한 최소 비용을 구해야한다 는 조건
 * -> dp[i]는 i명의 고객을 늘리기 위한 최소 비용
 *
 *
 * */

public class BOJ_1106_호텔 {

    static BufferedReader br;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine().trim());
        int potentialCustomer = Integer.parseInt(st.nextToken()); // 잠재적 고객 변수명을 못짓겠음
        int cityCnt = Integer.parseInt(st.nextToken()); // 도시의 개수 N

        // 각 도시에서 비용으로 얻을 수 있는 고객 수는 100명 이하
        // 적어도 C명을 늘려야 하므로 그보다 더 큰 고객을 들였을 때 비용이 더 작을 수 있다.
        int[] dp = new int[potentialCustomer + 100]; // 잠재적 고객 정보가 들어있는 dp 테이블
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int idx = 0; idx < cityCnt; idx++){
            st = new StringTokenizer(br.readLine().trim());
            int cost = Integer.parseInt(st.nextToken());    // 비용
            int customer = Integer.parseInt(st.nextToken()); // 고객

            for (int jdx = customer; jdx < potentialCustomer + 100; jdx++) {
                // 돈에 정수배 만큼 투자할 수 있으므로
                // 현재 비용 + dp[j-customer]로 j명의 고객을 늘린다.
                if (dp[jdx-customer] != Integer.MAX_VALUE) { // 무한이라면, 아직 갱신되지 않은 값이므로 고객을 확보할 수 없다.
                    dp[jdx] = Math.min(dp[jdx], cost + dp[jdx - customer]);
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int idx = potentialCustomer; idx < potentialCustomer + 100 ; idx++){
            answer = Math.min(answer, dp[idx]);
        }
        System.out.println(answer);
    }
}
