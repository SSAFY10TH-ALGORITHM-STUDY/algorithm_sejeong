
/**
 코니는 매일 다른 옷 조합
 코니 각 종류별로 최대 1가지 의상만 착용 가능 -> 동시 착용 불가
 착용한 의상의 일부 겹쳐도, 다른 의상이 겹치지 않거나, 의상을 추가로 더 착용한 경우 -> 서로 다른 방법으로 옷 착용
 코니 하루에 최소 한개의 의상 입는다.

 알아가는 개념 -> containsKey

 */

import java.util.*;
import java.io.*;

class PGMS_42578_의상 {

    static HashMap<String, ArrayList<String>> map;

    public int solution(String[][] clothes) {
        int answer = 1;
        map = new HashMap<>();

        for(int idx = 0; idx < clothes.length; idx++){
            String 옷종류 = clothes[idx][1];
            String 옷상세 = clothes[idx][0];

            // 만약 옷 종류가 최초 생성된다면"?
            if (!map.containsKey(옷종류)){
                ArrayList<String> list = new ArrayList<>();
                map.put(옷종류, list);
            }

            // list에다 옷상세 넣기
            map.get(옷종류).add(옷상세);
        }

        // 경우의 수 구하기
        // (의상수 + 1) * ,,, * (의상수 + 1) -1
        // +1을 해주는 이유 : 해당 의상 종류를 입지 않는 경우의 수
        // -1을 해주는 이유 : 전체 다 벗는 경우는 없음
        for(String 옷종류 : map.keySet()){
            // 옷 리스트의 개수 -> 각 옷들의 조합
            answer *= (map.get(옷종류).size() + 1);

        }

        return answer - 1; // 최소 한개의 의상은 입는다.
    }
}