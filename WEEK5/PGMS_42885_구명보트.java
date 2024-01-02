package WEEK5;
import java.util.*;
public class PGMS_42885_구명보트 {

    public int solution(int[] people, int limit) {
        int answer = 0;

        Arrays.sort(people);

        int start = 0;
        int end = people.length -1;

        while(start <= end ){
            answer += 1;

            //움직이는 조건
            if (people[start] + people[end] <= limit){
                start += 1;
            }
            end -= 1;

        }

        return answer;
    }

}
