package WEEK6;

class PGMS_43105_정수삼각형 {
    public int solution(int[][] triangle) {
        int answer = 0;


        int size = triangle.length; // 삼각 형 높이

        int [][] dp = new int[size][size];

        // 가장 왼쪽 단계 미리 저장
        dp[0][0] = triangle[0][0];

        for (int idx = 1; idx < size; idx++){
            dp[idx][0] = dp[idx - 1][0] + triangle[idx][0];
        }

        // 위에서부터 높이 하나씩 내려가면서 최대값 구하기
        for (int rowIdx = 1; rowIdx < size; rowIdx++){
            for(int colIdx = 1; colIdx < triangle[rowIdx].length; colIdx++){
                dp[rowIdx][colIdx] = Math.max(dp[rowIdx - 1][colIdx - 1] + triangle[rowIdx][colIdx] , dp[rowIdx - 1][colIdx] + triangle[rowIdx][colIdx]);

            }
        }

        for (int idx = 0; idx < size; idx++){
            answer = Math.max(dp[size - 1][idx], answer);
        }


        return answer;

    }
}
