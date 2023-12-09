package WEEK2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ_1106_호텔
 * - 어떤 도시에서 9원을 들여 홍보하면 3명의 고객이 늘어난다.
 * - 호텔의 고객을 적어도 C명 늘이기 위해, 형택이가 투자해야 하는 돈의 최솟값을 구하는 프로그램
 * 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12
 *             3              6
 * 1  1  1  1  1  1  1  1  1  1   1   1
 * */

public class BOJ_1106_호텔 {

    static BufferedReader br;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine().trim());
        int C = Integer.parseInt(st.nextToken()); // 잠재적 고객 변수명을 못짓겠음
        int cityCnt = Integer.parseInt(st.nextToken()); // 도시의 개수 N

        int[] dp = new int[C + 1]; // 잠재적 고객 정보가 들어있는 dp 테이블

        for (int idx = 0; idx < cityCnt; idx++){
            st = new StringTokenizer(br.readLine().trim());
            int cost = Integer.parseInt(st.nextToken());    // 비용
            int customer = Integer.parseInt(st.nextToken()); // 고객

            for (int customerIdx = customer; customerIdx < C+1; customerIdx++){
                dp[customerIdx] = Math.min(dp[customerIdx], cost + dp[customerIdx - customer]);
            }

        }




    }
}
