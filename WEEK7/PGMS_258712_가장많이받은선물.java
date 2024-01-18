package WEEK7;

import java.util.*;

public class PGMS_258712_가장많이받은선물 {

    public int solution(String[] friends, String[] gifts) {
        int answer = 0;

        int friendsCnt = friends.length;
        HashMap<String, Integer> map = new HashMap<>();
        int[] giftList = new int[friendsCnt];
        int[][] giftArray = new int[friendsCnt][friendsCnt];

        // HashMap 찾기
        for (int idx = 0; idx < friendsCnt; idx++){
            map.put(friends[idx], idx);
        }

        // gift
        for (String gift : gifts){
            // A -> B
            String[] pair = gift.split(" ");
            // giftList 주고 받기
            giftList[map.get(pair[0])]++;
            giftList[map.get(pair[1])]--;
            // giftArray
            giftArray[map.get(pair[0])][map.get(pair[1])] ++;
        }


        //
        for (int fromIdx = 0; fromIdx < friendsCnt; fromIdx++){
            int count = 0;
            for (int toIdx = 0; toIdx < friendsCnt; toIdx++){
                if (fromIdx == toIdx) continue; // 고려 안함

                // 두 사람 사이에 더 많은 선물을 준 사람 -> 선물 하나 받기
                if (giftArray[fromIdx][toIdx] > giftArray[toIdx][fromIdx] ||
                        (giftArray[fromIdx][toIdx] == giftArray[toIdx][fromIdx] && giftList[fromIdx] > giftList[toIdx])
                ){
                    count ++;
                }

            }
            if (answer < count){
                answer = count;
            }

        }


        return answer;
    }

}
