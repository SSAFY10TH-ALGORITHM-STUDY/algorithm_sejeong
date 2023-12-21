package WEEK3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ_2473_세용액
 * 0에 가까운 용액 만들 것
 * 세종류로 0에 가까운 혼합 용액 만들기
 * 시간 복잡도 ? :
 * 두 용액 에서는 : 1,000,000,000 + 1,000,000,000 = 2,000,000,000 안에서 풀렸다. // 20억 int 21억~~~~~~
 * 세 용액은 : 1,000,000,000 * 3 이므로 int 값을 초과함 -> long type
 *
 * 반례
 5
 999999999 1000000000 1000000000 1000000000 1000000000
 5
 -999999999 -1000000000 -1000000000 -1000000000 -1000000000
 -1000000000 -1000000000 -999999999
 int 형으로 array를 받았었는데, sum을 진행하면서 int형 overflow 때문에 반례가 오답이 나타날 수 있다.
 따라서 array 받을 때 부터 Long 타입으로 받던가
 sum 더해주는 부분에서 (long)으로 감싸주어야 한다.
 *
 * */

public class BOJ_2473_세용액 {

    static BufferedReader br;
    static StringTokenizer st;

    static Long[] solutionList;
    static SpecialSolution result;

    // 특수 용액 만드는 조합
    static class SpecialSolution{
        long result;
        int solution1;
        int solution2;
        int solution3;
        public SpecialSolution(long result, int solution1, int solution2, int solution3){
            this.result = result;
            this.solution1 = solution1;
            this.solution2 = solution2;
            this.solution3 = solution3;
        }

        @Override
        public String toString() {
            return solutionList[solution1] + " "
                    + solutionList[solution2] + " "
                    + solutionList[solution3];
        }
    }

    // 이분 탐색 로직
    public static void binarySearch(int standard){
        // standard는 기장 왼쪽
        int start = standard + 1; // 가장 왼쪽을 제외한 startPoint
        int end = solutionList.length - 1; // 가장 오른쪽

        // 이분 탐색
        while(start < end) {
            long mixedSolution = solutionList[standard] + solutionList[start] + solutionList[end];

            // 만약 현재 결과보다 작다면 갱신
            if (Math.abs(mixedSolution) <= result.result){
                result.result = Math.abs(mixedSolution); // 갱신
                result.solution1 = standard;
                result.solution2 = start;
                result.solution3 = end;
            }

            // 좌 우 탐색
            if (mixedSolution < 0)
                start += 1;
            else
                end -= 1;
        }
    }

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));

        int solutionCnt = Integer.parseInt(br.readLine().trim());
        solutionList = new Long[solutionCnt]; //
        st = new StringTokenizer(br.readLine().trim());
        for (int idx = 0; idx < solutionCnt; idx++){
            solutionList[idx] = Long.parseLong(st.nextToken());
        }

        // 1. 투포인터 위한 solutionList 정렬
        Arrays.sort(solutionList);

        // 2. 이분 탐색
        // start와 end
        result = new SpecialSolution(Long.MAX_VALUE, 0, 0, 0); // 초기화

        // standard를 idx 다음 부터, start - end를 이용한 이분탐색
        for (int standard = 0; standard < solutionCnt; standard ++){
            binarySearch(standard); // standard를 기준으로 인덱스 탐색
        }

        System.out.println(result.toString());
    }


}
