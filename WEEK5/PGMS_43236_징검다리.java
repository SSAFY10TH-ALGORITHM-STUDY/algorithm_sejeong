package WEEK5;
import java.util.*;

/**
 * 66.7 -> 최종 성공 못함
 * */

public class PGMS_43236_징검다리 {

    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;


        // rock 정렬
        Arrays.sort(rocks);
        int start = 0;
        int end = distance;

        while(start <= end){
            // mid 값
            int mid = (start + end) / 2;
            int delStone = 0;
            int preStone = 0;

            for (int idx = 0; idx < rocks.length; idx++){
                if (rocks[idx] - preStone < mid){
                    delStone++;
                }
                else {
                    preStone = rocks[idx];
                }

                if (delStone > n){
                    break;
                }
            }

            // mid 값 찾기
            if (delStone <= n){
                answer = mid;
                start = mid + 1;
            }
            else{
                end = mid - 1;
            }
        }

        return answer;
    }

}
