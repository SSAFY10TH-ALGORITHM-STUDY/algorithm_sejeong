package WEEK4;

import java.util.*;

/**

 정렬 / H-Index
 h 값 -> 논문 n편 중 h번 이상 인용된 논문 h편 이상 / 나머지 논문이 h번 이하 인용되면 h의 최댓값 H-Index

 */


public class PGMS_42747_HIndex {

    public int solution(int[] citations) {
        int answer = 0;

        // 논문 정렬
        Arrays.sort(citations); // 오름차순 정렬
        for(int idx = 0; idx < citations.length; idx++){
            // 더 작은 것
            int hIndex = Math.min(citations[idx], citations.length-idx);
            answer = Math.max(answer, hIndex); // 최대값 찾기
        }



        return answer;
    }
}
