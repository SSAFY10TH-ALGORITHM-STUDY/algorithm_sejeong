package WEEK7;

import java.util.*;

/**
 한변의 길이가 1인 정삼각 형 -> 2n+1
 윗변의 길이 n / 아랫변의 길이 n+1
 */

public class PGMS_258705_산모양타일링 {


    public int solution(int n, int[] tops) {
        int answer = 0;

        int MOD = 10007; // 경우의 수

        int [][] dp = new int[100001][2];

        dp[0][0] = 1;
        for (int idx = 0; idx < n; idx++){
            dp[idx + 1][0] = dp[idx][0] * (2 + tops[idx]) + dp[idx][1] * (1 + tops[idx]);
            dp[idx + 1][1] = dp[idx][0] + dp[idx][1];

            dp[idx + 1][0] %= MOD;
            dp[idx + 1][1] %= MOD;

        }

        answer = (dp[n][0] + dp[n][1]) % MOD;

        return answer;
    }
}
