package WEEK2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ_1749_점수 따먹기
 * https://lackofwillpower.tistory.com/56
 * 사실 이해 못했다 ,,, 하려고 해도 되지 않는다, ,,,,,,,,
 * */
public class BOJ_1749_점수따먹기 {

    static BufferedReader br;
    static StringTokenizer st;

    static int rowSize;
    static int colSize;
    static int [][] map;
    static int [][] dp;
    static int result;

    public static int prefixSum(int x1, int y1, int x2, int y2){
        return dp[x2][y2] - dp[x1 - 1][y2] - dp[x2][y1 - 1] + dp[x1 - 1][y1 - 1];
    }


    public static void findMaxSum(){

        for (int rowIdx = 1; rowIdx <= rowSize; rowIdx++){
            for (int colIdx = 1; colIdx <= colSize; colIdx++){
                for (int miniRowIdx = rowIdx; miniRowIdx <= rowSize; miniRowIdx++){
                    for (int miniColIdx = colIdx; miniColIdx <= colSize; miniColIdx++){
                        result = Math.max(result, prefixSum(rowIdx, colIdx, miniRowIdx, miniColIdx));
                    }
                }
            }
        }

    }


    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine().trim());
        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());

        map = new int[rowSize + 1][colSize + 1];
        dp = new int[rowSize + 1][colSize + 1];
        result = Integer.MIN_VALUE;

        for (int rowIdx = 1; rowIdx <= rowSize; rowIdx++){
            st = new StringTokenizer(br.readLine());
            for (int colIdx = 1; colIdx <= colSize; colIdx++){
                map[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
                dp[rowIdx][colIdx] = dp[rowIdx][colIdx-1] + dp[rowIdx-1][colIdx] - dp[rowIdx - 1][colIdx - 1] + map[rowIdx][colIdx];
            }
        }

        findMaxSum();
        System.out.println(result);

   }

}
